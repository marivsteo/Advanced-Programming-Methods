package Values;

public class IntValue implements Value {
	
	private int value;
	
	public IntValue()
	{
		this.value = 0;
	}
	
	public IntValue(int value_)
	{
		this.value = value_;
	}

	@Override
	public Type getType() {
		return new IntType();
	}
	
	public String toString()
	{
		return Integer.toString(this.value);
	}
	
	public boolean equals(Object other)
	{
		if (! (other instanceof IntValue))
			return false;
		IntValue auxOther = (IntValue)other;
		return auxOther.value == this.value;
	}

	public int getVal()
	{
		return this.value;
	}
	
	public Value copyValue()
	{
		return new IntValue(this.value);
	}
	
	@Override
	public Object makeCopy() {
		return new IntValue(this.value);
	}
}
