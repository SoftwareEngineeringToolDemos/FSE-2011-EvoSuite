package org.evosuite.symbolic.vm.math;

import org.evosuite.symbolic.expr.Operator;
import org.evosuite.symbolic.expr.RealExpression;
import org.evosuite.symbolic.expr.RealUnaryExpression;
import org.evosuite.symbolic.vm.SymbolicEnvironment;


public final class EXP extends MathFunction_D2D {

	private static final String EXP = "exp";

	public EXP(SymbolicEnvironment env) {
		super(env, EXP);
	}

	@Override
	protected RealExpression executeFunction(double res) {
		Operator op = Operator.EXP;
		return new RealUnaryExpression(realExpression, op, res);
	}

}