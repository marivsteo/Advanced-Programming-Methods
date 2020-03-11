package Statements;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import ADTs.MyIDictionary;
import ADTs.MyIHeap;
import Exceptions.MyException;
import Expressions.Exp;
import ProgramState.PrgState;
import Values.IntType;
import Values.StringType;
import Values.StringValue;
import Values.Type;
import Values.Value;

public class openRFileStmt implements IStmt {

	private Exp exp;
	
	public openRFileStmt(Exp exp_)
	{
		this.exp = exp_;
	}
	
	@Override
	public PrgState execute(PrgState state) throws MyException {
		MyIDictionary<String, Value> dict = state.getDict();
		MyIDictionary<String, BufferedReader> fileTable = state.getFileTable();
		MyIHeap<Value> heap = state.getHeap();
		Value value = exp.eval(dict, heap);
		if (value.getType() instanceof StringType)
		{
			if (fileTable.contains(((StringValue) value).getVal()))
				throw new MyException("openRFileStmt Exception: String is already in the FileTable!");
			try
			{
				Reader reader = new FileReader(((StringValue)(value)).getVal());
				BufferedReader bufferedReader = new BufferedReader(reader);
				fileTable.put(((StringValue)(value)).getVal(), bufferedReader);
				return null;
			}
			catch(IOException e)
			{
				throw new MyException("IOException!");
			}
		}
		else
			throw new MyException("openRFileStmt Exception: Expression is not a String!");
	}

	@Override
	public String toString(MyIDictionary<String, Value> dict) throws MyException {
		return "OpenFile " + exp.toString();
	}

	@Override
	public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
		Type expType = this.exp.typecheck(typeEnv);
		if (expType.equals(new StringType()))
			return typeEnv;
		throw new MyException("OpenRFileStmt TypeCheck Error!");
	}

}
