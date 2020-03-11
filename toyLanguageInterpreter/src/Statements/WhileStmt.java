package Statements;

import ADTs.MyIDictionary;
import ADTs.MyIHeap;
import ADTs.MyIStack;
import Exceptions.MyException;
import Expressions.Exp;
import ProgramState.PrgState;
import Values.BoolType;
import Values.BoolValue;
import Values.IntType;
import Values.Type;
import Values.Value;

public class WhileStmt implements IStmt {
	
	private Exp exp;
	private IStmt stmt;
	
	public WhileStmt(Exp exp_, IStmt stmt_)
	{
		this.exp = exp_;
		this.stmt = stmt_;
	}
	

	@Override
	public PrgState execute(PrgState state) throws MyException {
		MyIDictionary<String, Value> dict = state.getDict();
		MyIHeap<Value> heap = state.getHeap();
		MyIStack<IStmt> stack = state.getStack();
		Value value = exp.eval(dict, heap);
		if (value.getType() instanceof BoolType)
		{
			BoolValue boolValue = (BoolValue) value;
			if (boolValue.getVal())
			{
				stack.push(this);
				stack.push(this.stmt);
			}
			return null;
		}
		else
			throw new MyException("While Statement Exception: Expression does not evaluate to a boolean value!");
	}

	@Override
	public String toString(MyIDictionary<String, Value> dict) throws MyException {
		return "While " + this.exp.toString() + " " + this.stmt.toString();
	}


	@Override
	public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
		Type expType = this.exp.typecheck(typeEnv);
		if (expType.equals(new BoolType()))
		{
			this.stmt.typecheck(typeEnv);
			return typeEnv;
		}
		throw new MyException("WhileStmt TypeCheck Error!");
	}

}
