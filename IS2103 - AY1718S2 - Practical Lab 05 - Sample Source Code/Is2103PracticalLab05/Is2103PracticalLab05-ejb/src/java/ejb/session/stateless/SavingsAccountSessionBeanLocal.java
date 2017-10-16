package ejb.session.stateless;

import java.math.BigDecimal;
import util.exception.AccountNotFoundException;
import util.exception.InsufficientBalanceException;



public interface SavingsAccountSessionBeanLocal
{
    public void creditSavingsAccount(Long savingsAccountId, BigDecimal amount) throws AccountNotFoundException;
    
    public void debitSavingsAccount(Long savingsAccountId, BigDecimal amount) throws AccountNotFoundException, InsufficientBalanceException;

    public void creditSavingsAccountRequiresNewTransaction(Long savingsAccountId, BigDecimal amount) throws AccountNotFoundException;
    
    public void debitSavingsAccountRequiresNewTransaction(Long savingsAccountId, BigDecimal amount) throws AccountNotFoundException, InsufficientBalanceException;
}