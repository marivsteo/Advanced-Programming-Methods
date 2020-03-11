package ADTs;

import java.util.Collection;
import java.util.Enumeration;
import java.util.Hashtable;

public interface MyIDictionary<K, V> {

	void put(K key, V value);
	
	public Collection<V> elements();
	
	public V get(K key);
	
	public boolean isEmpty();
	
	public Collection<K> keys();
	
	public V remove(K key);
	
	public int size();
	
	boolean contains(K key);
	
	String toString();
	
	public Object makeCopy();
}
