package Values;

public class RefType implements Type {
	
	private Type inner;
	
	public RefType(Type inner_)
	{
		this.inner = inner_;
	}
	
	public Type getInner()
	{
		return this.inner;
	}
	
	public boolean equals(Object another){
		if (another instanceof RefType)
			return this.inner.equals(((RefType) another).getInner());
		return false;
	}
	
	public String toString()
	{
		return "Ref(" + this.inner.toString() + ")";
	}

	@Override
	public Value defaultValue() {
		return new RefValue(0, this.inner);
	}

	@Override
	public Type makeCopy() {
		return this;
	}
}
