package ADTs;

import java.util.Collection;
import java.util.Map;

import Exceptions.MyException;
import Values.Value;

public interface MyIHeap<V> { //this represents not an heap data structure, but one that will be used for the simulation of HEAP MEMORY, being similar to a map

	void put(V value);
	
	public Collection<V> elements();
	
	public V get(int key);
	
	public boolean isEmpty();
	
	public Collection<Integer> keys();
	
	public V remove(int key);
	
	public int size();
	
	boolean contains(int key);
	
	String toString();
	
	int getLastPosition();
	
	void explicitPut(int key, V value);
	
	Map<Integer, V> entrySet();

	void addEntrySet(Map<Integer, V> newEntrySet) throws MyException;

	int generateNextPosition();
}
