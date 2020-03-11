package Expressions;

import ADTs.MyIDictionary;
import ADTs.MyIHeap;
import Exceptions.MyException;
import Values.BoolType;
import Values.RefType;
import Values.RefValue;
import Values.Type;
import Values.Value;

public class ReadHeapExp implements Exp {
	
	private Exp exp;
	
	public ReadHeapExp(Exp exp_)
	{
		this.exp = exp_;
	}

	@Override
	public Value eval(MyIDictionary<String, Value> map, MyIHeap<Value> heap) throws MyException {
		Value value = this.exp.eval(map, heap);
		if (!(value instanceof RefValue))
			throw new MyException("Reading from heap not using a referene value!");
		RefValue valueExp = (RefValue) value;
		Value heapValue = heap.get(valueExp.getAddress());
		if (heapValue == null) //the refvalue address is not found inside the heap
			throw new MyException("Reference Value Address is not found inside the heap!");
		return heapValue;
	}
	
	public String toString()
	{
		return "Read Heap " + exp.toString();
	}

	@Override
	public Type typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
		Type tp = this.exp.typecheck(typeEnv);
		if (!(tp instanceof RefType))
			throw new MyException("TypeChecker: ReahHeapExpression used with non-reference parameter!");
		RefType rfTp = (RefType) tp;
		return rfTp.getInner();
	}
	
}
