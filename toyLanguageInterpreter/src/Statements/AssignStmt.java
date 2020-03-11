package Statements;

import ADTs.MyIDictionary;
import ADTs.MyIHeap;
import Exceptions.MyException;
import Expressions.Exp;
import ProgramState.PrgState;
import Values.Type;
import Values.Value;

public class AssignStmt implements IStmt {
	
	private String id;
	private Exp exp;
	
	public AssignStmt(String id_, Exp exp_)
	{
		this.id = id_;
		this.exp = exp_;
	}

	@Override
	public PrgState execute(PrgState state) throws MyException{
		MyIDictionary<String, Value> dict = state.getDict();
		MyIHeap<Value> heap = state.getHeap();
		Value newValue = exp.eval(dict, heap);
		if (dict.contains(id))
		{
			Value oldValue = dict.get(id);
			if (newValue.getType().equals(oldValue.getType()))
				dict.put(id, newValue);
			else
				throw new MyException("The ID " + id + " of type " + oldValue.getType().toString() + " is attributed a wrong value type!");
		}
		else
			throw new MyException("The ID " + id + " was not declared before!");
		
		return null;
	}
	
	public String toString(MyIDictionary<String, Value> dict) throws MyException
	{
		return id + " = " + this.exp.toString();
	}

	@Override
	public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
		Type typeId = typeEnv.get(this.id);
		Type typeExp = this.exp.typecheck(typeEnv);
		if (typeId.equals(typeExp))
			return typeEnv;
		throw new MyException("Assignment: right hand side and left hand side have different types!");
	}

}
