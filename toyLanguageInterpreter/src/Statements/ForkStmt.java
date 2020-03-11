package Statements;

import java.io.BufferedReader;

import ADTs.MyIDictionary;
import ADTs.MyIHeap;
import ADTs.MyIList;
import ADTs.MyIStack;
import ADTs.MyStack;
import Exceptions.MyException;
import ProgramState.PrgState;
import Values.Type;
import Values.Value;

public class ForkStmt implements IStmt {
	
	private IStmt istmt;
	
	public ForkStmt(IStmt istmt_)
	{
		this.istmt = istmt_;
	}

	@Override
	public PrgState execute(PrgState state) throws MyException {
		MyIDictionary<String, Value> dict = state.getDict();
		MyIHeap<Value> heap = state.getHeap();
		MyIDictionary<String, BufferedReader> fileTable = state.getFileTable();
		MyIList<Value> out = state.getOut();
		
		MyIStack<IStmt> newStack = new MyStack<IStmt>(this.istmt);
		MyIDictionary<String, Value> newDict = (MyIDictionary<String, Value>) dict.makeCopy();
		
		return new PrgState(newStack, newDict, out, this.istmt, fileTable, heap);
	}

	@Override
	public String toString(MyIDictionary<String, Value> dict) throws MyException {
		return "Fork(\t" + istmt.toString() + "\t)";
	}

	@SuppressWarnings("unchecked")
	@Override
	public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
		
		return (MyIDictionary<String, Type>) this.istmt.typecheck(typeEnv).makeCopy();
	}

}
