package Statements;

import ADTs.MyIDictionary;
import Exceptions.MyException;
import ProgramState.PrgState;
import Values.Type;
import Values.Value;

public class VarDeclStmt implements IStmt {
	
	String name;
	Type type;
	
	public VarDeclStmt(String name_, Type type_)
	{
		this.name = name_;
		this.type = type_;
	}

	@Override
	public PrgState execute(PrgState state) throws MyException {
		MyIDictionary<String, Value> dict = state.getDict();
		if (dict.contains(this.name))
			throw new MyException("The variable " + name + " was already declared!");
		dict.put(name, type.defaultValue());
		return null;
	}

	@Override
	public String toString(MyIDictionary<String, Value> dict) throws MyException {
		return type.toString() + " " + name;
	}

	@Override
	public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
		MyIDictionary<String,Type> newEnv= (MyIDictionary<String, Type>) typeEnv.makeCopy();
		newEnv.put(this.name, this.type);
		return newEnv;
	}

}
