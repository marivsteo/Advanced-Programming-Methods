package Values;

public class StringType implements Type {

	@Override
	public Value defaultValue() {
		return new StringValue("");
	}

	public String toString()
	{
		return "String";
	}
	
	public boolean equals(Object another){
		if (another instanceof StringType)
			return true;
		return false;
	}
	
	@Override
	public Type makeCopy() {
		return this;
	}
}
