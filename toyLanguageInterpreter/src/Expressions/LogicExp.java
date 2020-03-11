package Expressions;

import ADTs.MyIDictionary;
import ADTs.MyIHeap;
import Exceptions.MyException;
import Values.BoolType;
import Values.BoolValue;
import Values.IntType;
import Values.Type;
import Values.Value;

public class LogicExp implements Exp {
	
	private Exp e1, e2;
	char op; 
	
	LogicExp(char op_, Exp e1_, Exp e2_)
	{
		this.e1 = e1_;
		this.e2 = e2_;
		this.op = op_;
	}


	@Override
	public Value eval(MyIDictionary<String, Value> map, MyIHeap<Value> heap) throws MyException {
		Value v1, v2;
		
		v1 = e1.eval(map, heap);
		v2 = e2.eval(map, heap);
		if (v1.getType().equals(new BoolType()))
		{
			if (!(v2.getType().equals(new BoolType())))
				throw new MyException("Second operand is not a boolean!");
			BoolValue i1 = (BoolValue)v1;
			BoolValue i2 = (BoolValue)v2;
			boolean n1 = i1.getVal();
			boolean n2 = i2.getVal();
			if (op=='&') return new BoolValue(n1&&n2);
			if (op =='|') return new BoolValue(n1||n2);
			throw new MyException("The second parameter is not an operator!");
		}
		else
			throw new MyException("First operand is not a boolean!");
	}
	
	public String toString()
	{
		return this.e1.toString() + op + e2.toString();
	}
	
	@Override
	public Type typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
		Type tp1, tp2;
		
		tp1 = this.e1.typecheck(typeEnv);
		tp2 = this.e2.typecheck(typeEnv);
		if (!(tp1 instanceof BoolType) || !(tp1 instanceof BoolType))
			throw new MyException("TypeChecker: Logic Expression with non-boolean parameters!");
		return new BoolType();
	}

}
