package Expressions;

import ADTs.MyIDictionary;
import ADTs.MyIHeap;
import Exceptions.MyException;
import Values.BoolType;
import Values.BoolValue;
import Values.IntType;
import Values.IntValue;
import Values.Type;
import Values.Value;

public class RelationalExp implements Exp {

	private Exp e1, e2;
	String op;
	
	public RelationalExp(Exp e1_, Exp e2_, String op_)
	{
		this.e1 = e1_;
		this.e2 = e2_;
		this.op = op_;
	}
	
	
	
	@Override
	public Value eval(MyIDictionary<String, Value> map, MyIHeap<Value> heap) throws MyException 
	{
		Value v1, v2;
		
		v1 = e1.eval(map, heap);
		v2 = e2.eval(map, heap);
		if (v1.getType().equals(new IntType()))
		{
			if (!(v2.getType().equals(new IntType())))
				throw new MyException("Second operand is not an integer!");
			IntValue i1 = (IntValue)v1;
			IntValue i2 = (IntValue)v2;
			int n1 = i1.getVal();
			int n2 = i2.getVal();
			if (op.equals("==")) return new BoolValue(n1==n2);
			if (op.equals("!=")) return new BoolValue(n1!=n2);
			if (op.equals(">")) return new BoolValue(n1>n2);
			if (op.equals(">=")) return new BoolValue(n1>=n2);
			if (op.equals("<")) return new BoolValue(n1<n2);
			if (op.equals("<=")) return new BoolValue(n1<=n2);
			throw new MyException("Invalid opearator given to relational expressions!");
		}
		else
			throw new MyException("First operand is not an integer!");
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
		if (!(tp1 instanceof IntType) || !(tp1 instanceof IntType))
			throw new MyException("TypeChecker: Relational Expression with non-integers parameters!");
		return new BoolType();
	}

}
