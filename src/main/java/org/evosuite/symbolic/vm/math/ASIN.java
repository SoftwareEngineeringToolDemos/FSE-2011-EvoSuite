package org.evosuite.symbolic.vm.math;

import org.evosuite.symbolic.expr.Operator;
import org.evosuite.symbolic.expr.RealExpression;
import org.evosuite.symbolic.expr.RealUnaryExpression;
import org.evosuite.symbolic.vm.SymbolicEnvironment;


public final class ASIN extends MathFunction_D2D {

	private static final String ASIN = "asin";

	public ASIN(SymbolicEnvironment env) {
		super(env, ASIN);
	}

	public RealExpression executeFunction(double res) {
		Operator op = Operator.ASIN;
		return new RealUnaryExpression(realExpression, op, res);
	}

}