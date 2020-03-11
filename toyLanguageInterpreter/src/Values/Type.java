package Values;

public interface Type {
	
	boolean equals(Object other);
	
	String toString();
	
	Value defaultValue();

	Type makeCopy();

}
