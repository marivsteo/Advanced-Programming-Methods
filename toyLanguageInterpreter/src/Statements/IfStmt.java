package Statements;

import ADTs.MyIDictionary;
import ADTs.MyIHeap;
import ADTs.MyIStack;
import Exceptions.MyException;
import Expressions.Exp;
import ProgramState.PrgState;
import Values.BoolType;
import Values.BoolValue;
import Values.Type;
import Values.Value;

public class IfStmt implements IStmt {
	
	Exp cond;
	IStmt thenStatement, elseStatement;
	
	public IfStmt(Exp cond_, IStmt thenStatement_, IStmt elseStatement_)
	{
		this.cond = cond_;
		this.thenStatement = thenStatement_;
		this.elseStatement = elseStatement_;
	}

	@Override
	public PrgState execute(PrgState state) throws MyException {
		MyIDictionary<String, Value> dict = state.getDict();
		MyIStack<IStmt> stack = state.getStack();
		MyIHeap<Value> heap = state.getHeap();
		Value valueCond = this.cond.eval(dict, heap);
		
		if (!(valueCond.getType() instanceof BoolType))
			throw new MyException("If statement used with String or Integer as Condition!");
		BoolValue boolCond = (BoolValue)cond.eval(dict, heap);
		if (boolCond.getVal() == true)
			stack.push(thenStatement);
		else
			stack.push(elseStatement);
		return null;
	}

	public String toString(MyIDictionary<String, Value> dict) throws MyException
	{
		return "If " + this.cond.toString() + "\tThen "+ this.thenStatement.toString(dict) + "\tElse " + this.elseStatement.toString(dict);
	}

	@Override
	public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
		Type typeExp = this.cond.typecheck(typeEnv);
		if (typeExp.equals(new BoolType()))
		{
			this.thenStatement.typecheck(typeEnv);
			this.elseStatement.typecheck(typeEnv);
			return typeEnv;
		}
		throw new MyException("The condition of IF has not the type bool");
	}
}
