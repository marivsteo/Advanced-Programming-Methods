package Statements;

import ADTs.MyIDictionary;
import Exceptions.MyException;
import ProgramState.PrgState;
import Values.Type;
import Values.Value;

public class NopStmt implements IStmt {
	
	public NopStmt() {}

	@Override
	public PrgState execute(PrgState state) throws MyException {
		return null;
	}

	@Override
	public String toString(MyIDictionary<String, Value> dict) throws MyException {
		return "NOP";
	}

	@Override
	public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
		return typeEnv;
	}

}
