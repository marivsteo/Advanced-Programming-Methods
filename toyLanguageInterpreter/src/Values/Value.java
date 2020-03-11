package Values;

public interface Value {
	
	Type getType();

	String toString();
	
	boolean equals(Object other);
	
	Value copyValue();
	
	public Object makeCopy();
}
