package ADTs;

import Exceptions.MyException;
import Values.Value;

public interface MyIStack<T> {

	T pop();
	void push(T elem);
	T peek();
	boolean empty();
	int search(T elem);
	
	String toString(MyIDictionary<String, Value> symTable) throws MyException; //needed by the ProgramState toString() method
}
