package Expressions;

import ADTs.MyIDictionary;
import ADTs.MyIHeap;
import Exceptions.MyException;
import Values.Type;
import Values.Value;

public interface Exp {

	Value eval(MyIDictionary<String,Value> map, MyIHeap<Value> heap) throws MyException;
	
	String toString();
	
	Type typecheck(MyIDictionary<String,Type> typeEnv) throws MyException;
}
