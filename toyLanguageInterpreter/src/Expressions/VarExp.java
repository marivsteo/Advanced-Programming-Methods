package Expressions;

import ADTs.MyIDictionary;
import ADTs.MyIHeap;
import Exceptions.MyException;
import Values.Type;
import Values.Value;

public class VarExp implements Exp {

	private String id;
	
	public VarExp(String id_)
	{
		this.id = id_;
	}
	
	@Override
	public Value eval(MyIDictionary<String, Value> map, MyIHeap<Value> heap) throws MyException {
		if (!(map.contains(this.id)))
			throw new MyException("Variable used, but not declared!");
		return map.get(this.id);
	}

	public String toString()
	{
		return this.id;
	}
	
	@Override
	public Type typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
		return typeEnv.get(this.id);
	}
}
