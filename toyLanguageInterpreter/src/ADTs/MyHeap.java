package ADTs;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import Exceptions.MyException;
import Values.Value;

import java.util.Collection;
import java.lang.Integer; 

public class MyHeap<V> implements MyIHeap<V> {
	
	private HashMap<Integer, V> dict;
	private int nextPosition;
	
	public MyHeap()
	{
		this.nextPosition = 0;
		this.dict = new HashMap<Integer, V>();
	}
	
	public synchronized int generateNextPosition()
	{
		this.nextPosition += 1;
		return this.nextPosition;
	}

	@Override
	public void put(V value) {
		this.nextPosition += 1;
		this.dict.put(Integer.valueOf(this.nextPosition), value);
		
	}

	@Override
	public Collection<V> elements() {
		return this.dict.values();
	}

	@Override
	public V get(int key) {
		return this.dict.get(Integer.valueOf(key));
	}

	@Override
	public boolean isEmpty() {
		return this.dict.isEmpty();
	}

	@Override
	public Collection<Integer> keys() {
		return this.dict.keySet();
	}

	@Override
	public V remove(int key) {
		return this.dict.remove(Integer.valueOf(key));
	}

	@Override
	public int size() {
		return this.dict.size();
	}

	@Override
	public boolean contains(int key)
	{
		return this.dict.containsKey(Integer.valueOf(key));
	}
	
	@Override
	public String toString()
	{
		String result = "";
		
		Iterator<Integer> it = (Iterator<Integer>) this.dict.keySet().iterator(); 
        
        while(it.hasNext()) 
        { 
             Integer key = it.next(); 
             V value = this.dict.get(key);
             result += key.toString() + "\t" + value.toString() + "\n";
        } 
        return result;
	}

	@Override
	public int getLastPosition() {
		return this.nextPosition;
	}

	@Override
	public void explicitPut(int key, V value) {
		this.dict.put(Integer.valueOf(key), value);
		
	}

	@Override
	public Map<Integer, V> entrySet() {
		return this.dict;
	}

	@Override
	public void addEntrySet(Map<Integer, V> newEntrySet) throws MyException {
		if (!(newEntrySet instanceof HashMap))
			throw new MyException("addEntrySet used not with a HashMap");
		this.dict = (HashMap<Integer, V>) newEntrySet;
		
	}

}
