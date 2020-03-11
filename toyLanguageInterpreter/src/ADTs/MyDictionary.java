package ADTs;

import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import Values.Type;
import Values.Value;

public class MyDictionary<K, V> implements MyIDictionary<K, V> {
	
	private HashMap<K, V> dict;
	
	public MyDictionary()
	{
		this.dict = new HashMap<K, V>();
	}

	@Override
	public void put(K key, V value) {
		this.dict.put(key, value);
		
	}

	@Override
	public Collection<V> elements() {
		return this.dict.values();
	}

	@Override
	public V get(K key) {
		return this.dict.get(key);
	}

	@Override
	public boolean isEmpty() {
		return this.dict.isEmpty();
	}

	@Override
	public Collection<K> keys() {
		return this.dict.keySet();
	}

	@Override
	public V remove(K key) {
		return this.dict.remove(key);
	}

	@Override
	public int size() {
		return this.dict.size();
	}

	@Override
	public boolean contains(K key)
	{
		return this.dict.containsKey(key);
	}
	
	@Override
	public String toString()
	{
		String result = "";
		
		Iterator<K> it = (Iterator<K>) this.dict.keySet().iterator(); 
        
        while(it.hasNext()) 
        { 
             K key = it.next(); 
             V value = this.dict.get(key);
             result += key.toString() + "\t" + value.toString() + "\n";
        } 
        return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object makeCopy() {
		HashMap<K, V> newDict = new HashMap<K, V>();
		
		Iterator<K> it = (Iterator<K>) this.dict.keySet().iterator(); 
		while(it.hasNext()) 
        { 
             K key = it.next(); 
             V value = this.dict.get(key);
             if (value instanceof Value)
            	 newDict.put(key, (V) ( (Value) value).makeCopy());
             else if (value instanceof Type)
            	 newDict.put(key, (V) ( (Type) value).makeCopy());
             //the method makeCopy works only when V is either of Type Value or Type!
        }
		
		MyDictionary<K, V> result = new MyDictionary<K, V>();
		result.setEntrySet(newDict);
		
		return result;
	}
	
	public void setEntrySet(HashMap<K, V> newDict)
	{
		this.dict = newDict;
	}
}
