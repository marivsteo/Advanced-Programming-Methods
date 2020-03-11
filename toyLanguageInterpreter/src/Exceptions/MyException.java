package Exceptions;

@SuppressWarnings("serial")
public class MyException extends Exception{
	
	public MyException() {}
	
	public MyException(String str)
	{
		super(str);
	}

}
