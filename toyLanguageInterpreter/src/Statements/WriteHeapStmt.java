package Statements;

import ADTs.MyIDictionary;
import ADTs.MyIHeap;
import Exceptions.MyException;
import Expressions.Exp;
import ProgramState.PrgState;
import Values.IntValue;
import Values.RefType;
import Values.RefValue;
import Values.Type;
import Values.Value;

public class WriteHeapStmt implements IStmt {
	
	private String variable; //name of a RefValue, representing a heap address
	private Exp exp; //the new value to be stored in the heap
	
	public WriteHeapStmt(String variable_, Exp exp_)
	{
		this.variable = variable_;
		this.exp = exp_;
	}

	@Override
	public PrgState execute(PrgState state) throws MyException {
		MyIDictionary<String, Value> dict = state.getDict();
		MyIHeap<Value> heap = state.getHeap();
		Value value = dict.get(this.variable);
		if (value == null)
			throw new MyException(this.variable + " is not found in the Symbol Table!");
		if (!(value instanceof RefValue))
			throw new MyException(this.variable + " is not of reference type!");
		RefValue refValue = (RefValue) value;
		Value expValue = this.exp.eval(dict, heap);
		if (refValue.getLocationType().equals(expValue.getType()))
		{
			int address = refValue.getAddress();
			heap.explicitPut(address, expValue.copyValue());
			return null;
		}
		else
			throw new MyException(this.variable + " and " + expValue.toString() + "do not have compatible types!");
	}

	@Override
	public String toString(MyIDictionary<String, Value> dict) throws MyException {
		return "Write Heap" + this.variable + " " + this.exp.toString();
	}

	@Override
	public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
		Type typeExp = this.exp.typecheck(typeEnv);
		Type varType = typeEnv.get(this.variable);
		if (!(varType.equals(new RefType(typeExp))))
			throw new MyException("WriteHeapStmt TypeCheck Error!");
		return typeEnv;
	}

}
