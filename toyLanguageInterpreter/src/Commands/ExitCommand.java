package Commands;

public class ExitCommand extends Command {

	public ExitCommand(String key_, String description_) {
		super(key_, description_);
	}

	@Override
	public void execute() {
		System.exit(0);
	}
}
