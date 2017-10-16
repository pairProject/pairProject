package ejb.session.stateless;

import entity.SavingsAccount;
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
@Local(SavingsAccountSessionBeanLocal.class)

public class SavingsAccountSessionBean implements SavingsAccountSessionBeanLocal
{
    @PersistenceContext(unitName = "Is2103PracticalLab05-ejbPU")
    private EntityManager em;
    
    
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @Override
    public void creditSavingsAccount(Long savingsAccountId, BigDecimal amount) throws AccountNotFoundException
    {
        SavingsAccount savingsAccount = em.find(SavingsAccount.class, savingsAccountId);
        
        if(savingsAccount != null)
        {
            savingsAccount.setBalance(savingsAccount.getBalance().add(amount));
        }
        else
        {
            throw new AccountNotFoundException("Savings account id " + savingsAccountId + " does not exist");
        }
    }
    
    
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @Override
    public void debitSavingsAccount(Long savingsAccountId, BigDecimal amount) throws AccountNotFoundException, InsufficientBalanceException
    {
        SavingsAccount savingsAccount = em.find(SavingsAccount.class, savingsAccountId);
        
        if(savingsAccount != null)
        {
            if(savingsAccount.getBalance().compareTo(amount) >= 0)
            {
                savingsAccount.setBalance(savingsAccount.getBalance().subtract(amount));
            }
            else
            {
                throw new InsufficientBalanceException("Savings account id " + savingsAccountId + " has insufficient balance of " + savingsAccount.getBalance() + " versus debiting amount of " + amount);
            }
        }
        else
        {
            throw new AccountNotFoundException("Savings account id " + savingsAccountId + " does not exist");
        }
    }
    
    
    
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    @Override
    public void creditSavingsAccountRequiresNewTransaction(Long savingsAccountId, BigDecimal amount) throws AccountNotFoundException
    {
        SavingsAccount savingsAccount = em.find(SavingsAccount.class, savingsAccountId);
        
        if(savingsAccount != null)
        {
            savingsAccount.setBalance(savingsAccount.getBalance().add(amount));
        }
        else
        {
            throw new AccountNotFoundException("Savings account id " + savingsAccountId + " does not exist");
        }
    }
    
    
    
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    @Override
    public void debitSavingsAccountRequiresNewTransaction(Long savingsAccountId, BigDecimal amount) throws AccountNotFoundException, InsufficientBalanceException
    {
        SavingsAccount savingsAccount = em.find(SavingsAccount.class, savingsAccountId);
        
        if(savingsAccount != null)
        {
            if(savingsAccount.getBalance().compareTo(amount) >= 0)
            {
                savingsAccount.setBalance(savingsAccount.getBalance().subtract(amount));
            }
            else
            {
                throw new InsufficientBalanceException("Savings account id " + savingsAccountId + " has insufficient balance of " + savingsAccount.getBalance() + " versus debiting amount of " + amount);
            }
        }
        else
        {
            throw new AccountNotFoundException("Savings account id " + savingsAccountId + " does not exist");
        }
    }
}