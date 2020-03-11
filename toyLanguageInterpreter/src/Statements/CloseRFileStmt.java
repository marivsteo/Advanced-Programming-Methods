package Statements;

import java.io.BufferedReader;
import java.io.IOException;

import ADTs.MyIDictionary;
import ADTs.MyIHeap;
import Exceptions.MyException;
import Expressions.Exp;
import ProgramState.PrgState;
import Values.Value;
import Values.IntType;
import Values.StringType;
import Values.StringValue;
import Values.Type;

public class CloseRFileStmt implements IStmt {
	
	Exp exp;
	
	public CloseRFileStmt(Exp exp_)
	{
		this.exp = exp_;
	}

	@Override
	public PrgState execute(PrgState state) throws MyException {
		MyIDictionary<String, Value> dict = state.getDict();
		MyIDictionary<String, BufferedReader> fileTable = state.getFileTable();
		MyIHeap<Value> heap = state.getHeap();
		Value value = this.exp.eval(dict, heap);
		if(!(value.getType() instanceof StringType))
			throw new MyException("CloseRFileStmt Exception:" + exp.toString() + "is not evaluated to a String!");
		StringValue valueFileString = (StringValue)value;
		if (! fileTable.contains(valueFileString.getVal()))
			throw new MyException("readFileStmt Exception: " + this.exp.toString() + " is not found in the File Table!");
		BufferedReader bufferedReader = fileTable.get(valueFileString.getVal());
		try
		{
			bufferedReader.close();
			fileTable.remove(valueFileString.getVal());
			return null;
		}
		catch (IOException e)
		{
			throw new MyException("IOException!");
		}
	}

	@Override
	public String toString(MyIDictionary<String, Value> dict) throws MyException {
		return "Close File " + this.exp.toString();
	}

	@Override
	public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
		Type expType = this.exp.typecheck(typeEnv);
		if (expType.equals(new StringType()))
			return typeEnv;
		throw new MyException("CloseRFileStmt TypeCheck Error!");
	}

}
