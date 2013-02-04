package org.evosuite.statistics;

import java.util.ArrayList;
import java.util.List;

import org.evosuite.Properties;
import org.evosuite.testsuite.TestSuiteChromosome;
import org.evosuite.utils.LoggingUtils;

public abstract class SequenceOutputVariableFactory<T extends Number> {

	protected String name;
	
	protected List<Long> timeStamps = new ArrayList<Long>();
	protected List<T> values = new ArrayList<T>();
	
	private long startTime = 0L;
	
	public SequenceOutputVariableFactory(String name) {
		this.name = name;
	}
	
	public void setStartTime(long time) {
		this.startTime = time;
	}
	
	protected abstract T getValue(TestSuiteChromosome individual);
	
	public void update(TestSuiteChromosome individual) {
		timeStamps.add(System.currentTimeMillis() - startTime);
		values.add(getValue(individual));
	}
	
	public List<String> getVariableNames() {
		List<String> variables = new ArrayList<String>();
		
		for(String suffix : getTimelineHeaderSuffixes()) {
			variables.add(name + suffix);
		}
		
		return variables;
			
	}
	
	public List<OutputVariable<T>> getOutputVariables() {
		List<OutputVariable<T>> variables = new ArrayList<OutputVariable<T>>();

		for(String variableName : getVariableNames()) {
			OutputVariable<T> variable = new OutputVariable<T>(variableName, getTimeLineValue(variableName));
			variables.add(variable);
		}

		return variables;
	}
	
	private T getTimeLineValue(String name) {
		long interval = Properties.TIMELINE_INTERVAL;
		
		int index = Integer.parseInt( (name.split("_T"))[1] );
		long preferredTime = interval * index;
		
		/*
		 * No data. Is it even possible? Maybe if population is too large,
		 * and budget was not enough to get even first generation
		 */
		if(timeStamps.isEmpty()){
			return (T) new Integer(0); // FIXXME - what else?
		}
		
		for(int i=0; i<timeStamps.size(); i++){
			/*
			 * find the first stamp that is after the time we would like to
			 * get coverage from
			 */
			long stamp = timeStamps.get(i);
			if(stamp < preferredTime){
				continue;
			}
			
			if(i==0){
				/*
				 * it is the first element, so not much to do, we just use it as value
				 */
				return values.get(i);
			}
			
			/*
			 * Now we interpolate the coverage, as usually we don't have the value for exact time we want
			 */
			long timeDelta = timeStamps.get(i) - timeStamps.get(i-1);
			LoggingUtils.getEvoLogger().info("Time delta: "+timeDelta);

			if(timeDelta > 0 ){
				double covDelta = values.get(i).doubleValue() - values.get(i-1).doubleValue();
				double ratio = covDelta / timeDelta;
				
				long diff = preferredTime - timeStamps.get(i-1);
				Double cov = values.get(i-1).doubleValue() +  (diff * ratio);
				return (T)cov; // TODO...type
			}
		}
		
		/*
		 * No time stamp was higher. This might happen if coverage is 100% and we stop search.
		 * So just return last value seen
		 */
		
		return values.get(values.size()-1);
	}

	
	private String[] getTimelineHeaderSuffixes(){
		int numberOfIntervals = calculateNumberOfIntervals();
		String[] suffixes = new String[numberOfIntervals]; 
		for(int i=0; i<suffixes.length; i++){
			/*
			 * NOTE: we start from T1 and not T0 because, by definition, coverage
			 * at T0 is equal to T0, and no point in showing it in a graph
			 */
			suffixes[i] = "_T"+(i+1);
		}
		return suffixes;
	}


	private int calculateNumberOfIntervals() {
		long interval = Properties.TIMELINE_INTERVAL;
		/*
		 * TODO: this might need refactoring once we choose 
		 * a different way to handle search timeouts.
		 * 
		 * The point here is that we need to support both if we use time
		 * as search budget, and fitness/statement evaluations.
		 * We cannot just look at the obtained history, because the search might
		 * have finished earlier, eg if 100% coverage
		 */
		long totalTime = Properties.GLOBAL_TIMEOUT * 1000l;
		
		int numberOfIntervals = (int) (totalTime / interval);
		return numberOfIntervals;
	}
}