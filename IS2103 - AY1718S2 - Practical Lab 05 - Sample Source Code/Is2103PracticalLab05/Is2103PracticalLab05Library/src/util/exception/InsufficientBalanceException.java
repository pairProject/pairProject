package util.exception;



public class InsufficientBalanceException extends Exception
{
    public InsufficientBalanceException()
    {
    }
    
    
    
    public InsufficientBalanceException(String msg)
    {
        super(msg);
    }
}