package Values;

public class StringValue implements Value {
	
	private String value;
	
	StringValue()
	{
		this.value = "";
	}
	
	public StringValue(String value_)
	{
		this.value = value_;
	}

	@Override
	public Type getType() {
		return new StringType();
	}

	public String toString()
	{
		return (this.value);
	}
	
	public boolean equals(Object other)
	{
		if (! (other instanceof StringValue))
			return false;
		StringValue auxOther = (StringValue)other;
		return auxOther.value.equals(this.value);
	}
	
	public String getVal()
	{
		return this.value;
	}
	
	public Value copyValue()
	{
		return new StringValue(this.value);
	}
	
	@Override
	public Object makeCopy() {
		return new StringValue(this.value);
	}
}
