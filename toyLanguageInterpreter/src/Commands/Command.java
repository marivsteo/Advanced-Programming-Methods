package Commands;

public abstract class Command {
	
	private String key, description;
	
	public Command(String key_, String description_)
	{ 
		this.key = key_; 
		this.description = description_;
	}
	
	public abstract void execute();
	
	public String getKey() {return key;}
	
	public String getDescription() {return description;}

}
