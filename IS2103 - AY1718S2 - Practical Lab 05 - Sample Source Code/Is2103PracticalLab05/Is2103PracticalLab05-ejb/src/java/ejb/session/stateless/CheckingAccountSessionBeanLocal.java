package ejb.session.stateless;

import java.math.BigDecimal;
import util.exception.AccountNotFoundException;
import util.exception.InsufficientBalanceException;



public interface CheckingAccountSessionBeanLocal
{
    public void creditCheckingAccount(Long checkingAccountId, BigDecimal amount) throws AccountNotFoundException;
    
    public void debitCheckingAccount(Long checkingAccountId, BigDecimal amount) throws AccountNotFoundException, InsufficientBalanceException;
    
    public void creditCheckingAccountRequiresNewTransaction(Long checkingAccountId, BigDecimal amount) throws AccountNotFoundException;
    
    public void debitCheckingAccountRequiresNewTransaction(Long checkingAccountId, BigDecimal amount) throws AccountNotFoundException, InsufficientBalanceException;

}