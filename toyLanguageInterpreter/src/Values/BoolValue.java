package Values;

public class BoolValue implements Value {

	private boolean value;
	
	public BoolValue()
	{
		this.value = false;
	}
	
	public BoolValue(boolean value_)
	{
		this.value = value_;
	}

	@Override
	public Type getType() {
		return new BoolType();
	}
	
	public String toString()
	{
		return Boolean.toString(this.value);
	}
	
	public boolean equals(Object other)
	{
		if (! (other instanceof BoolValue))
			return false;
		BoolValue auxOther = (BoolValue)other;
		return auxOther.value == this.value;
	}
	
	public boolean getVal()
	{
		return this.value;
	}
	
	public Value copyValue()
	{
		return new BoolValue(this.value);
	}

	@Override
	public Object makeCopy() {
		return new BoolValue(this.value);
	}
}
