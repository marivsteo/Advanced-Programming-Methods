package Statements;

import java.io.BufferedReader;
import java.io.IOException;

import ADTs.MyIDictionary;
import ADTs.MyIHeap;
import Exceptions.MyException;
import Expressions.Exp;
import ProgramState.PrgState;
import Values.IntType;
import Values.IntValue;
import Values.StringType;
import Values.StringValue;
import Values.Type;
import Values.Value;

public class readFile implements IStmt {
	
	private Exp exp;
	private String varName;
	

	public readFile(Exp exp_, String varName_)
	{
		this.exp = exp_;
		this.varName = varName_;
	}
	
	@Override
	public PrgState execute(PrgState state) throws MyException {
		MyIDictionary<String, Value> dict = state.getDict();
		MyIDictionary<String, BufferedReader> fileTable = state.getFileTable();
		MyIHeap<Value> heap = state.getHeap();
		if (!dict.contains(varName))
			throw new MyException("readFileStmt Exception: " + this.varName + " not declared!");
		Value valueVar = dict.get(varName);
		if (!( valueVar.getType() instanceof IntType))
			throw new MyException("readFileStmt Exception: " + this.varName + " is not an integer!");
		Value valueFile = this.exp.eval(dict, heap);
		if (!( valueFile.getType() instanceof StringType))
			throw new MyException("readFileStmt Exception: " + this.exp.toString() + " is not a string!");
		StringValue valueFileString = (StringValue) valueFile;
		if (! fileTable.contains(valueFileString.getVal()))
			throw new MyException("readFileStmt Exception: " + this.exp.toString() + " is not found in the File Table!");
		BufferedReader bufferedReader = fileTable.get(valueFileString.getVal());
		try
		{
			String line = bufferedReader.readLine();
			int readValue;
			if (line == null)
				readValue = 0;
			else
				readValue = Integer.parseInt(line);
			dict.put(varName, new IntValue(readValue));
			return null;
		}
		catch (IOException e)
		{
			throw new MyException("IOException!");
		}
	}

	@Override
	public String toString(MyIDictionary<String, Value> dict) throws MyException {
		return "Read File " + this.exp.toString() + " " + this.varName;
	}

	@Override
	public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
		Type expType = this.exp.typecheck(typeEnv);
		Type varType = typeEnv.get(this.varName);
		if (expType.equals(new StringType()) && varType.equals(new IntType()))
			return typeEnv;
		throw new MyException("CloseRFileStmt TypeCheck Error!");
	}

}
