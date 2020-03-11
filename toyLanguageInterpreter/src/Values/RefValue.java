package Values;

public class RefValue implements Value {
	
	private int address;
	private Type locationType;
	
	public RefValue(int address_, Type locationType_)
	{
		this.address = address_;
		this.locationType = locationType_;
	}
	
	public void setAddress(int address_)
	{
		this.address = address_;
	}
	
	public int getAddress()
	{
		return this.address;
	}
	
	public Type getLocationType()
	{
		return this.locationType;
	}
	
	
	@Override
	public Type getType()
	{
		return new RefType(this.locationType);
	}

	public String toString()
	{
		return Integer.toString(this.address) + " " + this.locationType.toString();
	}
	
	public boolean equals(Object other)
	{
		if (! (other instanceof RefValue))
			return false;
		RefValue auxOther = (RefValue)other;
		return auxOther.address == this.address;
	}
	
	public Value copyValue()
	{
		return new RefValue(this.address, this.locationType);
	}
	
	@Override
	public Object makeCopy() {
		return new RefValue(this.address, this.locationType);
	}
}
