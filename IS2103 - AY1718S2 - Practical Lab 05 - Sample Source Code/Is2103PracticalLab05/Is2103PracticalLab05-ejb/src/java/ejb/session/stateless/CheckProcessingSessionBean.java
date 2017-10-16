package ejb.session.stateless;

import java.math.BigDecimal;
import java.text.NumberFormat;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.EJBContext;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import util.exception.AccountNotFoundException;
import util.exception.EncashCheckException;
import util.exception.InsufficientBalanceException;



@Stateless
@Local(CheckProcessingSessionBeanLocal.class)
@Remote(CheckProcessingSessionBeanRemote.class)

public class CheckProcessingSessionBean implements CheckProcessingSessionBeanLocal, CheckProcessingSessionBeanRemote
{
    @Resource
    private EJBContext eJBContext;
    
    @EJB
    private CheckingAccountSessionBeanLocal checkingAccountSessionBeanLocal;
    @EJB
    private SavingsAccountSessionBeanLocal savingsAccountSessionBeanLocal;
    @EJB
    private HistoryLogSessionBeanLocal historyLogSessionBeanLocal;
    
    
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void encashCheck(Long checkingAccountId, Long savinngsAccountId, BigDecimal amount) throws EncashCheckException
    {
        try
        {
            checkingAccountSessionBeanLocal.debitCheckingAccount(checkingAccountId, amount);
            savingsAccountSessionBeanLocal.creditSavingsAccount(savinngsAccountId, amount);
            historyLogSessionBeanLocal.createHistoryLogEntry("Encashing check from checking account id " + checkingAccountId + " to savings accout id " + savinngsAccountId + " for the amount " + NumberFormat.getCurrencyInstance().format(amount));
        }
        catch(AccountNotFoundException | InsufficientBalanceException ex)
        {
            eJBContext.setRollbackOnly();
            throw new EncashCheckException(ex.getMessage());
        }
    }
    
    
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void encashCheckRequiresNewTransaction(Long checkingAccountId, Long savinngsAccountId, BigDecimal amount) throws EncashCheckException
    {
        try
        {
            checkingAccountSessionBeanLocal.debitCheckingAccountRequiresNewTransaction(checkingAccountId, amount);
            savingsAccountSessionBeanLocal.creditSavingsAccountRequiresNewTransaction(savinngsAccountId, amount);
            historyLogSessionBeanLocal.createHistoryLogEntryRequiresNewTransaction("Encashing check from checking account id " + checkingAccountId + " to savings accout id " + savinngsAccountId + " for the amount " + NumberFormat.getCurrencyInstance().format(amount));
        }
        catch(AccountNotFoundException | InsufficientBalanceException ex)
        {
            eJBContext.setRollbackOnly();
            throw new EncashCheckException(ex.getMessage());
        }
    }
}