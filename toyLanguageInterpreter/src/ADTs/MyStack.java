package ADTs;

import java.util.Iterator;
import java.util.Stack;

import Exceptions.MyException;
import Statements.IStmt;
import Values.Value;

public class MyStack<T> implements MyIStack<T> {

	private Stack<T> stack;
	
	public MyStack()
	{
		this.stack = new Stack<T>();
	}
	
	public MyStack(T initElem)
	{
		this.stack = new Stack<T>();
		this.stack.push(initElem);
	}
	
	@Override
	public T pop() {
		return this.stack.pop();
	}

	@Override
	public void push(T elem) {
		this.stack.push(elem);
		
	}

	@Override
	public T peek() {
		return this.stack.peek();
	}

	@Override
	public boolean empty() {
		return this.stack.empty();
	}

	@Override
	public int search(T elem) {
		return this.stack.search(elem);
	}
	
	@Override
	public String toString(MyIDictionary<String, Value> symTable) throws MyException
	{
		String result = "";
		String[] tokens;
		
		Iterator<T> it = (Iterator<T>)this.stack.iterator();
		while (it.hasNext())
		{
			T elem = it.next();
			result += ((IStmt)elem).toString(symTable) + "#";
		}
		
		if (result.contentEquals(""))
			return "";
		
		//because this.stack is a stack(ofc it is a stack xD ), we have to reverse the result String, so we will print the elements in the correct order
		tokens = result.split("#");
		result = "";
		for (int index = tokens.length - 1; index >= 0; --index)
			result += tokens[index] + "\n";
		
		return result;
	}
}
