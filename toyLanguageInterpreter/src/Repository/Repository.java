package Repository;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Vector;

import Exceptions.MyException;
import ProgramState.PrgState;

public class Repository<T> implements IRepository<T> {
	
	private List<T> elems;
	private String filePath;
	
	@SuppressWarnings("unchecked")
	public Repository(String filePath_)
	{
		this.elems = new Vector<T>();
		this.filePath = filePath_;
		try
		{
			//before we begin the program, we create the file and EMPTY it of possible previous content
			PrintWriter writer = new PrintWriter(this.filePath);
			writer.print("");
			writer.close();
		}
		catch (IOException e)
		{}
	}

	@Override
	public int getLength() {
		return this.elems.size();
	}

	@Override
	public void add(T elem) throws MyException{
		this.elems.add(elem);
	}

	@Override
	public void remove(T elem) {
		for (int index = 0; index < this.elems.size(); index++)
		{	
			if (elem.equals(this.elems.get(index)))
			{
				this.elems.remove(index);
				return;
			}
		}
	}

	@Override
	public void update(T elem) {
		for (int index = 0; index < this.elems.size(); index++)
		{	
			
			if (elem.equals(this.elems.get(index)))
			{
				this.elems.add(index, elem);
				return;
			}
		}
	}

	@Override
	public T getElement(int pos) throws MyException {
		if (pos >= this.elems.size())
			throw new MyException("Invalid position!\n");
		return this.getElement(pos);
	}

	@Override
	public void remove(int index) throws MyException {
		if (index < this.elems.size())
		{
			this.elems.remove(index);
		}
		else
			throw new MyException("Deleting from an invalid position from repositoy!\n");
	}

	@Override
	public void logPrgStateExec(PrgState prgState) throws MyException {
		try 
		{
			PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(this.filePath, true)));
			logFile.write(prgState.toString() + "\n\n");
			logFile.close();
		}
		catch (IOException e) 
		{throw new MyException("I/O Exception!");}
		
	}
	
	public List<T> getPrgList()
	{
		return this.elems;
	}
	
	public void setPrgList(List<T> newPrgList)
	{
		this.elems = newPrgList;
	}

}
