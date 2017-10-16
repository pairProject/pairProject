package ejb.session.stateless;

import entity.CheckingAccount;
import java.math.BigDecimal;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import util.exception.AccountNotFoundException;
import util.exception.InsufficientBalanceException;

@Stateless
@Local(CheckingAccountSessionBeanLocal.class)

public class CheckingAccountSessionBean implements CheckingAccountSessionBeanLocal
{
    @PersistenceContext(unitName = "Is2103PracticalLab05-ejbPU")
    private EntityManager em;
    
    
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @Override
    public void creditCheckingAccount(Long checkingAccountId, BigDecimal amount) throws AccountNotFoundException
    {
        CheckingAccount checkingAccount = em.find(CheckingAccount.class, checkingAccountId);
        
        if(checkingAccount != null)
        {
            checkingAccount.setBalance(checkingAccount.getBalance().add(amount));
        }
        else
        {
            throw new AccountNotFoundException("Checking account id " + checkingAccountId + " does not exist");
        }
    }
    
    
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @Override
    public void debitCheckingAccount(Long checkingAccountId, BigDecimal amount) throws AccountNotFoundException, InsufficientBalanceException
    {
        CheckingAccount checkingAccount = em.find(CheckingAccount.class, checkingAccountId);
        
        if(checkingAccount != null)
        {
            if(checkingAccount.getBalance().compareTo(amount) >= 0)
            {
                checkingAccount.setBalance(checkingAccount.getBalance().subtract(amount));
            }
            else
            {
                throw new InsufficientBalanceException("Checking account id " + checkingAccountId + " has insufficient balance of " + checkingAccount.getBalance() + " versus debiting amount of " + amount);
            }
        }
        else
        {
            throw new AccountNotFoundException("Checking account id " + checkingAccountId + " does not exist");
        }
    }
    
    
    
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    @Override
    public void creditCheckingAccountRequiresNewTransaction(Long checkingAccountId, BigDecimal amount) throws AccountNotFoundException
    {
        CheckingAccount checkingAccount = em.find(CheckingAccount.class, checkingAccountId);
        
        if(checkingAccount != null)
        {
            checkingAccount.setBalance(checkingAccount.getBalance().add(amount));
        }
        else
        {
            throw new AccountNotFoundException("Checking account id " + checkingAccountId + " does not exist");
        }
    }
    
    
    
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    @Override
    public void debitCheckingAccountRequiresNewTransaction(Long checkingAccountId, BigDecimal amount) throws AccountNotFoundException, InsufficientBalanceException
    {
        CheckingAccount checkingAccount = em.find(CheckingAccount.class, checkingAccountId);
        
        if(checkingAccount != null)
        {
            if(checkingAccount.getBalance().compareTo(amount) >= 0)
            {
                checkingAccount.setBalance(checkingAccount.getBalance().subtract(amount));
            }
            else
            {
                throw new InsufficientBalanceException("Checking account id " + checkingAccountId + " has insufficient balance of " + checkingAccount.getBalance() + " versus debiting amount of " + amount);
            }
        }
        else
        {
            throw new AccountNotFoundException("Checking account id " + checkingAccountId + " does not exist");
        }
    }
}