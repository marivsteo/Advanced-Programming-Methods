package ADTs;

import java.util.Iterator;
import java.util.LinkedList;

public class MyList<T> implements MyIList<T> {

	LinkedList<T> list;
	
	public MyList()
	{
		this.list = new LinkedList<T>();
	}
	
	@Override
	public void addFirst(T elem) {
		//add to the tail
		this.list.addFirst(elem);
		
	}

	@Override
	public void addLast(T elem) {
		this.list.addLast(elem);
		
	}

	@Override
	public boolean contains(T elem) {
		return this.list.contains(elem);
	}

	@Override
	public T get(int index) {
		return this.list.get(index);
	}

	@Override
	public T getFirst(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T getLast(int index) {
		return this.list.getLast();
	}

	@Override
	public int indexOf(T elem) {
		return this.list.indexOf(elem);
	}

	@Override
	public int lastIndexOf(T elem) {
		return this.list.lastIndexOf(elem);
	}

	@Override
	public T remove() {
		//removes the head
		return this.list.remove();
	}

	@Override
	public T set(int index, T elem) {
		return this.list.set(index, elem);
	}

	@Override
	public int size() {
		return this.list.size();
	}
	
	@Override
	public String toString()
	{
		String result = "";
		
		Iterator<T> it = (Iterator<T>)this.list.iterator();
		while (it.hasNext())
			result += it.next().toString() + "\n";
		return result;
	}
}
