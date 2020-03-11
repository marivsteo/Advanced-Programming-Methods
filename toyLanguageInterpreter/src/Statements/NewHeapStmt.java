package Statements;

import ADTs.MyIDictionary;
import ADTs.MyIHeap;
import Exceptions.MyException;
import Expressions.Exp;
import ProgramState.PrgState;
import Values.RefType;
import Values.RefValue;
import Values.Type;
import Values.Value;

public class NewHeapStmt implements IStmt {
	
	private String variable;
	private Exp exp;
	
	public NewHeapStmt(String variable_, Exp exp_)
	{
		this.variable = variable_;
		this.exp = exp_;
	}

	@Override
	public PrgState execute(PrgState state) throws MyException {
		MyIDictionary<String, Value> dict = state.getDict();
		MyIHeap<Value> heap = state.getHeap();
		Value value = dict.get(this.variable);
		if (!(value instanceof RefValue))
			throw new MyException(this.variable + " is not of reference type!");
		RefValue refValue = (RefValue) value;
		Value expValue = this.exp.eval(dict, heap);
		if (refValue.getLocationType().equals(expValue.getType()))
		{
			int address = heap.generateNextPosition();
			refValue.setAddress(address);
			heap.explicitPut(address, expValue.copyValue());
			return null;
		}
		else
			throw new MyException(this.variable + " and " + expValue.toString() + "do not have compatible types!");
	}

	@Override
	public String toString(MyIDictionary<String, Value> dict) throws MyException {
		return this.variable + "= New " + this.exp.toString();
	}

	@Override
	public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
		Type typeVar = typeEnv.get(this.variable);
		Type typeExp = this.exp.typecheck(typeEnv);
		if (typeVar.equals(new RefType(typeExp)))
		{
			return typeEnv;
		}
		throw new MyException("NewHeapStmt TypeCheck Error!");
	}

}
