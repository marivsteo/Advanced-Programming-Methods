package ADTs;

public interface MyIList<T> {
	
	void addFirst(T elem);
	
	void addLast(T elem);
	
	boolean contains(T elem);
	
	T get(int index);
	
	T getFirst(int index);
	
	T getLast(int index);
	
	int indexOf(T elem);
	
	int lastIndexOf(T elem);
	
	T remove();
	
	T set(int index, T elem);
	
	int size();
	
	String toString(); //needed by the ProgramState toString() method
}
