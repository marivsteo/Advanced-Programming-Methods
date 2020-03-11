package Statements;

import ADTs.MyIDictionary;
import ADTs.MyIStack;
import Exceptions.MyException;
import ProgramState.PrgState;
import Values.Type;
import Values.Value;

public class CompStmt implements IStmt {
	
	IStmt first;
	IStmt second;
	
	public CompStmt(IStmt first_, IStmt second_)
	{
		this.first = first_;
		this.second = second_;
	}

	@Override
	public PrgState execute(PrgState state) {
		MyIStack<IStmt> aux = state.getStack();
		aux.push(this.second);
		aux.push(this.first);
		return null;
	}
	
	public String toString(MyIDictionary<String, Value> dict) throws MyException
	{
		return "-" + first.toString(dict) + "\n-" + second.toString(dict);
	}

	@Override
	public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
		
		return this.second.typecheck(this.first.typecheck(typeEnv));
	}
	
	
}
