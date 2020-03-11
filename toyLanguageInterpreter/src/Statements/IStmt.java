package Statements;

import ADTs.MyIDictionary;
import Exceptions.MyException;
import ProgramState.PrgState;
import Values.Type;
import Values.Value;

public interface IStmt {
	
	PrgState execute(PrgState state) throws MyException;

	String toString(MyIDictionary<String, Value> dict) throws MyException;
	
	MyIDictionary<String,Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException;
}
