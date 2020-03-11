package Values;

public class IntType implements Type {
	
	public boolean equals(Object another){
		if (another instanceof IntType)
			return true;
		return false;
	}

	public String toString()
	{
		return "Integer";
	}

	@Override
	public Value defaultValue() {
		return new IntValue(0);
	}
	
	@Override
	public Type makeCopy() {
		return this;
	}
}
