package Commands;

import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Scanner;
import ADTs.MyDictionary;
import ADTs.MyIDictionary;

public class TextMenu {
	
	private MyIDictionary<String, Command> commands;
	
	public TextMenu()
	{ 
		this.commands = new MyDictionary<String, Command>();
	}

	public void addCommand(Command c)
	{ 
		this.commands.put(c.getKey(), c);
	}
	
	private void printMenu()
	{
		Iterator<String> it = this.commands.keys().iterator();
		while (it.hasNext())
		{
			String key = it.next();
			Command com = this.commands.get(key);
			String line = String.format("%4s : %s", key, com.getDescription());
			System.out.println(line);
		}
	}
		
	public void show(){
		@SuppressWarnings("resource")
		Scanner scanner=new Scanner(System.in);
		while(true){
			this.printMenu();
			System.out.printf("Input the option: ");
			String key = scanner.nextLine();
			Command com= commands.get(key);
			if (com==null)
			{
				System.out.println("Invalid Option");
				continue;
			}
			com.execute();
		}
	}

}
