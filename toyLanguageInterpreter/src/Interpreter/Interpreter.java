package Interpreter;

import ADTs.MyDictionary;
import ADTs.MyIDictionary;
import ADTs.MyStack;
import Commands.*;
import Controller.Controller;
import Exceptions.MyException;
import Expressions.ArithExp;
import Expressions.ReadHeapExp;
import Expressions.RelationalExp;
import Expressions.ValueExp;
import Expressions.VarExp;
import ProgramState.PrgState;
import Repository.Repository;
import Statements.AssignStmt;
import Statements.CloseRFileStmt;
import Statements.CompStmt;
import Statements.ForkStmt;
import Statements.IStmt;
import Statements.IfStmt;
import Statements.NewHeapStmt;
import Statements.NopStmt;
import Statements.PrintStmt;
import Statements.VarDeclStmt;
import Statements.WhileStmt;
import Statements.WriteHeapStmt;
import Statements.openRFileStmt;
import Statements.readFile;
import Values.BoolType;
import Values.BoolValue;
import Values.IntType;
import Values.IntValue;
import Values.RefType;
import Values.StringType;
import Values.StringValue;
import Values.Type;

public class Interpreter {
	
	public static void main(String[] args)
	{
		//hardcoding the first example...
		IStmt stmt1= new CompStmt(new VarDeclStmt("v",new IntType()),
				new CompStmt(new AssignStmt("v",new ValueExp(new IntValue(2))), new PrintStmt(new VarExp("v"))));
		try { stmt1.typecheck(new MyDictionary<String, Type>()); } catch (MyException e1) { e1.printStackTrace(); }
		MyStack<IStmt> stack1 = new MyStack<IStmt>(stmt1);
		PrgState prg1 = new PrgState(stack1);
		Controller ctrl1 = new Controller(new Repository<PrgState>("file1.txt"));
		try
		{
			ctrl1.addProgramState(prg1);
		}
		catch (MyException e)
		{
			System.out.println(e.toString());
		}
		
		//example 2
		IStmt stmt2= new CompStmt( new VarDeclStmt("a",new IntType()), new CompStmt(new VarDeclStmt("b",new IntType()),
				new CompStmt(new AssignStmt("a", new ArithExp('+',new ValueExp(new IntValue(2)),new 
				ArithExp('*',new ValueExp(new IntValue(3)), new ValueExp(new IntValue(5))))),
				new CompStmt(new AssignStmt("b",new ArithExp('+',new VarExp("a"), new
				ValueExp(new IntValue(1)))), new PrintStmt(new VarExp("b"))))));
		try { stmt2.typecheck(new MyDictionary<String, Type>()); } catch (MyException e1) { e1.printStackTrace(); }
		MyStack<IStmt> stack2 = new MyStack<IStmt>(stmt2);
		PrgState prg2 = new PrgState(stack2);
		Controller ctrl2 = new Controller(new Repository<PrgState>("file2.txt"));
		try
		{
			ctrl2.addProgramState(prg2);
		}
		catch (MyException e)
		{
			System.out.println(e.toString());
		}
		
		//example 3
		IStmt stmt3= new CompStmt(new VarDeclStmt("a",new BoolType()),
				new CompStmt(new VarDeclStmt("v", new IntType()),
				new CompStmt(new AssignStmt("a", new ValueExp(new BoolValue(true))),
				new CompStmt(new IfStmt(new VarExp("a"),new AssignStmt("v",new ValueExp(new
				IntValue(2))), new AssignStmt("v", new ValueExp(new IntValue(3)))), new PrintStmt(new
				VarExp("v"))))));
		try { stmt3.typecheck(new MyDictionary<String, Type>()); } catch (MyException e1) { e1.printStackTrace(); }
		MyStack<IStmt> stack3 = new MyStack<IStmt>(stmt3);
		PrgState prg3 = new PrgState(stack3);
		Controller ctrl3 = new Controller(new Repository<PrgState>("file3.txt"));
		try
		{
			ctrl3.addProgramState(prg3);
		}
		catch (MyException e)
		{
			System.out.println(e.toString());
		}
		
		//example 4
		IStmt stmt4= new CompStmt(new VarDeclStmt("a",new BoolType()),
				new CompStmt(new VarDeclStmt("v", new IntType()),
				new CompStmt(new AssignStmt("a", new ValueExp(new BoolValue(true))),
				new CompStmt(new IfStmt(new RelationalExp(new ValueExp(new IntValue(4)), new ValueExp(new IntValue(4)), ">="),
				new AssignStmt("v",new ValueExp(new
				IntValue(2))), new AssignStmt("v", new ValueExp(new IntValue(3)))), new PrintStmt(new
				VarExp("v"))))));
		try { stmt4.typecheck(new MyDictionary<String, Type>()); } catch (MyException e1) { e1.printStackTrace(); }
		MyStack<IStmt> stack4 = new MyStack<IStmt>(stmt4);
		PrgState prg4 = new PrgState(stack4);
		Controller ctrl4 = new Controller(new Repository<PrgState>("file4.txt"));
		try
		{
			ctrl4.addProgramState(prg4);
		}
		catch (MyException e)
		{}
		
		//example5
		IStmt stmt5 = new CompStmt(new VarDeclStmt("varf", new StringType()),
				new CompStmt(new AssignStmt("varf", new ValueExp(new StringValue("test.in"))),
                new CompStmt(new openRFileStmt(new VarExp("varf")),
                new CompStmt(new VarDeclStmt("varc", new IntType()),
                new CompStmt(new readFile(new VarExp("varf"), "varc"),
                new CompStmt(new PrintStmt(new VarExp("varc")),
                new CompStmt(new readFile(new VarExp("varf"), "varc"),
                new CompStmt(new PrintStmt(new VarExp("varc")),
                new CloseRFileStmt(new VarExp("varf"))))))))));
		MyStack<IStmt> stack5 = new MyStack<IStmt>(stmt5);
		try { stmt5.typecheck(new MyDictionary<String, Type>()); } catch (MyException e1) { e1.printStackTrace(); }
		PrgState prg5 = new PrgState(stack5);
		Controller ctrl5 = new Controller(new Repository<PrgState>("file5.txt"));
		try
		{
			ctrl5.addProgramState(prg5);
		}
		catch (MyException e)
		{
			System.out.println(e.toString());
		}
		
		//example 6
		IStmt stmt6 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())),
				new CompStmt(new NewHeapStmt("v", new ValueExp(new IntValue(3))), new NewHeapStmt("v", new ValueExp(new IntValue(5)))));	
		try { stmt6.typecheck(new MyDictionary<String, Type>()); } catch (MyException e1) { e1.printStackTrace(); }
		MyStack<IStmt> stack6 = new MyStack<IStmt>(stmt6);
		PrgState prg6 = new PrgState(stack6);
		Controller ctrl6 = new Controller(new Repository<PrgState>("file6.txt"));
		try
		{
			ctrl6.addProgramState(prg6);
		}
		catch (MyException e)
		{
			System.out.println(e.toString());
		}
		
		//example7
		IStmt stmt7 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())),
                 new CompStmt(new NewHeapStmt("v", new ValueExp(new IntValue(20))),
                 new CompStmt(new PrintStmt(new ReadHeapExp(new VarExp("v"))),
                 new CompStmt(new WriteHeapStmt("v", new ValueExp(new IntValue(30))),
                 new PrintStmt(new ArithExp('+', new ReadHeapExp(new VarExp("v")), new ValueExp(new IntValue(5))))))));
		try { stmt7.typecheck(new MyDictionary<String, Type>()); } catch (MyException e1) { e1.printStackTrace(); }
		MyStack<IStmt> stack7 = new MyStack<IStmt>(stmt7);
		PrgState prg7 = new PrgState(stack7);
		Controller ctrl7 = new Controller(new Repository<PrgState>("file7.txt"));
		try
		{
			ctrl7.addProgramState(prg7);
		}
		catch (MyException e)
		{
			System.out.println(e.toString());
		}
		
		//example8
		IStmt stmt8 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())),
                new CompStmt(new NewHeapStmt("v", new ValueExp(new IntValue(20))),
                new CompStmt(new VarDeclStmt("a", new RefType(new RefType(new IntType()))),
                new CompStmt(new NewHeapStmt("a", new VarExp("v")),
                new CompStmt(new NewHeapStmt("v", new ValueExp(new IntValue(30))),
                new PrintStmt(new ReadHeapExp(new ReadHeapExp(new VarExp("a")))))))));
		try { stmt8.typecheck(new MyDictionary<String, Type>()); } catch (MyException e1) { e1.printStackTrace(); }
		MyStack<IStmt> stack8 = new MyStack<IStmt>(stmt8);
		PrgState prg8 = new PrgState(stack8);
		Controller ctrl8 = new Controller(new Repository<PrgState>("file8.txt"));
		try
		{
			ctrl8.addProgramState(prg8);
		}
		catch (MyException e)
		{
			System.out.println(e.toString());
		}
		
		//example9
		IStmt stmt9 = new CompStmt(new VarDeclStmt("v", new IntType()),
                new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(4))),
                new CompStmt(new WhileStmt(new RelationalExp(new VarExp("v"), new ValueExp(new IntValue(0)), ">="), 
				new CompStmt(new PrintStmt(new VarExp("v")), new AssignStmt("v", new ArithExp('-', new VarExp("v"), new ValueExp(new IntValue(1)))))),
                new PrintStmt(new VarExp("v")))));
		try { stmt9.typecheck(new MyDictionary<String, Type>()); } catch (MyException e1) { e1.printStackTrace(); }
		MyStack<IStmt> stack9 = new MyStack<IStmt>(stmt9);
		PrgState prg9 = new PrgState(stack9);
		Controller ctrl9 = new Controller(new Repository<PrgState>("file9.txt"));
		try
		{
			ctrl9.addProgramState(prg9);
		}
		catch (MyException e)
		{
			System.out.println(e.toString());
		}
		
		//example 10
		IStmt stmt10 = new CompStmt(new VarDeclStmt("v", new IntType()),
                new CompStmt(new VarDeclStmt("a", new RefType(new IntType())),
                new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(10))),
                new CompStmt(new NewHeapStmt("a", new ValueExp(new IntValue(22))),
                new CompStmt(new ForkStmt(new CompStmt(new WriteHeapStmt("a", new ValueExp(new IntValue(30))),
                new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(32))),
                new CompStmt(new PrintStmt(new VarExp("v")),
                new PrintStmt(new ReadHeapExp(new VarExp("a"))))))),
                new CompStmt(new PrintStmt(new VarExp("v")),
                new PrintStmt(new ReadHeapExp(new VarExp("a")))))))));
		try { stmt10.typecheck(new MyDictionary<String, Type>()); } catch (MyException e1) { e1.printStackTrace(); }
		MyStack<IStmt> stack10 = new MyStack<IStmt>(stmt10);
		PrgState prg10 = new PrgState(stack10);
		Controller ctrl10 = new Controller(new Repository<PrgState>("file10.txt"));
		try
		{
			ctrl10.addProgramState(prg10);
		}
		catch (MyException e)
		{
			System.out.println(e.toString());
		}
		
		//example exam1
				IStmt stmt11 = new CompStmt(new VarDeclStmt("v", new IntType()),
		                new CompStmt(new VarDeclStmt("a", new RefType(new IntType())),
		                new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(10))),
		                new CompStmt(new NewHeapStmt("a", new ValueExp(new IntValue(22))),
		                new CompStmt(new ForkStmt(new CompStmt(new WriteHeapStmt("a", new ValueExp(new IntValue(30))),
		                new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(32))),
		                new CompStmt(new PrintStmt(new VarExp("v")),
		                new PrintStmt(new ReadHeapExp(new VarExp("a"))))))),
		                new CompStmt(new PrintStmt(new VarExp("v")),
		                new PrintStmt(new ReadHeapExp(new VarExp("a")))))))));
				try { stmt11.typecheck(new MyDictionary<String, Type>()); } catch (MyException e1) { e1.printStackTrace(); }
				MyStack<IStmt> stack11 = new MyStack<IStmt>(stmt11);
				PrgState prg11 = new PrgState(stack11);
				Controller ctrl11 = new Controller(new Repository<PrgState>("file11.txt"));
				try
				{
					ctrl11.addProgramState(prg11);
				}
				catch (MyException e)
				{
					System.out.println(e.toString());
				}
		
		TextMenu menu = new TextMenu();
		menu.addCommand(new ExitCommand("0", "exit"));
		menu.addCommand(new RunExampleCommand("1","Run 1st example",ctrl1));
		menu.addCommand(new RunExampleCommand("2","Run 2nd example",ctrl2));
		menu.addCommand(new RunExampleCommand("3","Run 3rd example",ctrl3));
		menu.addCommand(new RunExampleCommand("4","Run 4th example",ctrl4));
		menu.addCommand(new RunExampleCommand("5","Run 5th example",ctrl5));
		menu.addCommand(new RunExampleCommand("6","Run 6th example",ctrl6));
		menu.addCommand(new RunExampleCommand("7","Run 7th example",ctrl7));
		menu.addCommand(new RunExampleCommand("8","Run 8th example",ctrl8));
		menu.addCommand(new RunExampleCommand("9","Run 9th example",ctrl9));
		menu.addCommand(new RunExampleCommand("10","Run 10th example",ctrl10));
		menu.addCommand(new RunExampleCommand("11","Exam For",ctrl11));
		menu.show();
	}

}
