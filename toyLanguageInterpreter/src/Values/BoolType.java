package Values;

public class BoolType implements Type {
	
	public boolean equals(Object another){
		if (another instanceof BoolType)
			return true;
		return false;
	}

	public String toString()
	{
		return "Boolean";
	}

	@Override
	public Value defaultValue() {
		return new BoolValue(false);
	}

	@Override
	public Type makeCopy() {
		return this;
	}
	
}
