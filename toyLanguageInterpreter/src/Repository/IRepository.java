package Repository;

import java.util.List;

import Exceptions.MyException;
import ProgramState.PrgState;

public interface IRepository<T> {
	
	public int getLength();
	
	public void add(T elem) throws MyException;
	
	public void remove(T elem);
	
	public void remove(int index) throws MyException;
	
	public void update(T elem);
	
	public T getElement(int pos) throws MyException;
	
	void logPrgStateExec(PrgState prgState) throws MyException;
	
	public List<T> getPrgList();
	
	public void setPrgList(List<T> newPrgList);
	
}
