package Expressions;

import ADTs.MyIDictionary;
import ADTs.MyIHeap;
import Exceptions.DivisionZeroException;
import Exceptions.MyException;
import Values.IntType;
import Values.IntValue;
import Values.Type;
import Values.Value;

public class ArithExp implements Exp{
	
	private Exp e1, e2;
	char op;
	
	public ArithExp(char op_, Exp e1_, Exp e2_)
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
		if (v1.getType().equals(new IntType()))
		{
			if (!(v2.getType().equals(new IntType())))
				throw new MyException("Second operand is not an integer!");
			IntValue i1 = (IntValue)v1;
			IntValue i2 = (IntValue)v2;
			int n1 = i1.getVal();
			int n2 = i2.getVal();
			if (op=='+') return new IntValue(n1+n2);
			if (op =='-') return new IntValue(n1-n2);
			if(op=='*') return new IntValue(n1*n2);
			if (op == '/')
			{
				if (n2 == 0)
					throw new DivisionZeroException("Division by 0!");
				return new IntValue(n1/n2);
			}
			throw new MyException("The second parameter is not an operator!");
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
			throw new MyException("TypeChecker: Arithmetic Expression with non-integer parameters!");
		return new IntType();
	}

}
