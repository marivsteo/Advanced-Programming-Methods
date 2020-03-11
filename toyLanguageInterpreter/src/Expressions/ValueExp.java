package Expressions;

import ADTs.MyIDictionary;
import ADTs.MyIHeap;
import Exceptions.MyException;
import Values.Type;
import Values.Value;

public class ValueExp implements Exp {
	
	private Value val;
	
	public ValueExp(Value val_)
	{
		this.val = val_;
	}

	@Override
	public Value eval(MyIDictionary<String, Value> map, MyIHeap<Value> heap) throws MyException {
		return this.val;
	}

	public String toString()
	{
		return this.val.toString();
	}

	@Override
	public Type typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
		return this.val.getType();
	}
}
