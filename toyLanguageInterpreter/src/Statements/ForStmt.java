package Statements;

import ADTs.MyIDictionary;
import ADTs.MyIStack;
import Exceptions.MyException;
import Expressions.Exp;
import Expressions.RelationalExp;
import Expressions.ValueExp;
import Expressions.VarExp;
import ProgramState.PrgState;
import Values.BoolValue;
import Values.IntType;
import Values.IntValue;
import Values.Type;
import Values.Value;

public class ForStmt implements IStmt {

	private Exp exp1, exp2, exp3;
	private IStmt istmt;
	
	public ForStmt(Exp exp1_, Exp exp2_, Exp exp3_, IStmt istmt_)
	{
		this.exp1 = exp1_;
		this.exp2 = exp2_;
		this.exp3 = exp3_;
		this.istmt = istmt_;
	}


	@Override
	public PrgState execute(PrgState state) throws MyException {
		MyIStack<IStmt> stack = state.getStack();
		
		VarDeclStmt varDeclStmt = new VarDeclStmt("v",new IntType());
		AssignStmt assignStmt = new AssignStmt("v", this.exp1);
		AssignStmt assignStmtLoop = new AssignStmt("v", this.exp3);
		RelationalExp relationalExp = new RelationalExp(new VarExp("v"), this.exp2, "<");
		CompStmt compStmt = new CompStmt(this.istmt, assignStmtLoop);
		WhileStmt whileStmt = new WhileStmt(relationalExp, compStmt);
		
		stack.push(whileStmt);
		stack.push(assignStmt);
		stack.push(varDeclStmt);
		
		return null;
	}

	@Override
	public String toString(MyIDictionary<String, Value> dict) throws MyException {
		return "For " + this.exp1.toString() + "\n" + this.exp2.toString() + "\n" + this.exp3.toString();
	}

	@Override
	public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
		typeEnv.put("v", new IntType());
		this.exp1.typecheck(typeEnv);
		this.exp2.typecheck(typeEnv);
		this.exp3.typecheck(typeEnv);
		
		this.istmt.typecheck(typeEnv);
		
		return typeEnv;
	}}
