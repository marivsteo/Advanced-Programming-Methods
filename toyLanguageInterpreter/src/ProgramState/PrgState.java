package ProgramState;

import java.io.BufferedReader;

import ADTs.MyDictionary;
import ADTs.MyHeap;
import ADTs.MyIDictionary;
import ADTs.MyIHeap;
import ADTs.MyIList;
import ADTs.MyIStack;
import ADTs.MyList;
import Exceptions.MyException;
import Statements.IStmt;
import Values.Value;

public class PrgState {
	
	private MyIStack<IStmt> exeStack;
	private MyIDictionary<String, Value> symTable;
	private MyIList<Value> out;
	private IStmt originalProgram;
	private MyIDictionary<String, BufferedReader> fileTable;
	private MyIHeap<Value> heap;
	private static int id = 0;
	private int currentId;
	
	public static synchronized int addId(){
	      id += 1;
	      return id;
	  }

	public PrgState(MyIStack<IStmt> stack, MyIDictionary<String,Value> map, MyIList<Value> list, IStmt program, MyIDictionary<String, 
			BufferedReader> fileTable_, MyIHeap<Value> heap_)
	{
		this.exeStack = stack;
		this.symTable = map;
		this.out = list;
		this.originalProgram = program;
		this.fileTable = fileTable_;
		this.heap = heap_;
		this.currentId = addId();
	}
	
	public PrgState(MyIStack<IStmt> stack, MyIDictionary<String,Value> map, MyIList<Value> list, MyIDictionary<String,
			BufferedReader> fileTable_, MyIHeap<Value> heap_)
	{
		this.exeStack = stack;
		this.symTable = map;
		this.out = list;
		this.originalProgram = this.exeStack.peek();
		this.fileTable = fileTable_;
		this.heap = heap_;
		this.currentId = addId();
	}
	
	public PrgState(MyIStack<IStmt> stack)
	{
		this.exeStack = stack;
		this.symTable = new MyDictionary<String, Value>();
		this.out = new MyList<Value>();
		this.originalProgram = this.exeStack.peek();
		this.fileTable = new MyDictionary<String, BufferedReader>();
		this.heap = new MyHeap<Value>();
		this.currentId = addId();
	}
	
	public IStmt getProgram()
	{
		return this.originalProgram;
	}
	
	public MyIDictionary<String, BufferedReader> getFileTable()
	{
		return this.fileTable;
	}
	
	public MyIStack<IStmt> getStack()
	{
		return this.exeStack;
	}
	
	public MyIList<Value> getOut()
	{
		return this.out;
	}
	
	public MyIDictionary<String, Value> getDict()
	{
		return this.symTable;
	}
	
	public MyIHeap<Value> getHeap()
	{
		return this.heap;
	}
	
	public void setFileTable(MyIDictionary<String, BufferedReader> fileTable_)
	{
		this.fileTable = fileTable_;
	}
	
	public void setStack(MyIStack<IStmt> exeStack_)
	{
		this.exeStack = exeStack_;
	}
	
	public void setDict(MyIDictionary<String, Value> symTable_)
	{
		this.symTable = symTable_;
	}
	
	public void setList(MyIList<Value> out_)
	{
		this.out = out_;
	}
	
	public void setProgram(IStmt program)
	{
		this.originalProgram = program;
	}
	
	public void setHeap(MyIHeap<Value> heap_)
	{
		this.heap = heap_;
	}
	
	public String toString()
	{
		String result = Integer.toString(this.currentId) + " Execution Stack:\n";
		
		try {
			result += this.exeStack.toString(this.symTable) + "Symbols Table\n";
			result += this.symTable.toString() + "Output\n";
			result += this.out.toString() + "FileTable\n";
			result += this.fileTable.toString() + "Heap\n";
			result += this.heap.toString();
		} catch (MyException e)
		{
			System.out.println(e);
		}
		return result;
	}
	
	public boolean isNotCompleted()
	{
		if (this.exeStack.empty())
			return false;
		return true;
	}
	
	public PrgState oneStep() throws MyException
	{
		if (this.exeStack.empty())
			throw new MyException("Empty Execution Stack!");
		IStmt nextInstruction = (IStmt)this.exeStack.pop();
		return nextInstruction.execute(this);	
	}
}
