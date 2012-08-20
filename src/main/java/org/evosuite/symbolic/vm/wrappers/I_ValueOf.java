package org.evosuite.symbolic.vm.wrappers;

import org.evosuite.symbolic.expr.IntegerExpression;
import org.evosuite.symbolic.vm.Function;
import org.evosuite.symbolic.vm.NonNullReference;
import org.evosuite.symbolic.vm.SymbolicEnvironment;


public final class I_ValueOf extends Function {

	private static final String VALUE_OF = "valueOf";

	public I_ValueOf(SymbolicEnvironment env) {
		super(env, Types.JAVA_LANG_INTEGER, VALUE_OF, Types.INT_TO_INTEGER);
	}

	private IntegerExpression bv32;

	@Override
	public void INVOKESTATIC() {
		bv32 = env.topFrame().operandStack.peekBv32();
	}

	@Override
	public void CALL_RESULT(Object conc_integer) {
		NonNullReference symb_integer = (NonNullReference) env.topFrame().operandStack
				.peekRef();
		env.heap.putField(Types.JAVA_LANG_INTEGER, "$intValue", conc_integer,
				symb_integer, bv32);
	}

}