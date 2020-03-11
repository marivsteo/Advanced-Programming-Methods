package Statements;

import ADTs.MyIDictionary;
import ADTs.MyIHeap;
import ADTs.MyIList;
import Exceptions.MyException;
import Expressions.Exp;
import ProgramState.PrgState;
import Values.Type;
import Values.Value;

public class PrintStmt implements IStmt {
	
	Exp exp;
	
	public PrintStmt(Exp exp_)
	{
		this.exp = exp_;
	}

	@Override
	public PrgState execute(PrgState state) throws MyException {
		MyIDictionary<String, Value> dict = state.getDict();
		MyIList<Value> list = state.getOut();
		MyIHeap<Value> heap = state.getHeap();
		list.addLast(exp.eval(dict, heap));
		return null;
	}

	public String toString(MyIDictionary<String, Value> dict) throws MyException //we cannot compute expression value without SymTable
	{
		return "Print " + this.exp.toString();
	}

	@Override
	public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
		this.exp.typecheck(typeEnv);
		return typeEnv;
	}
}
