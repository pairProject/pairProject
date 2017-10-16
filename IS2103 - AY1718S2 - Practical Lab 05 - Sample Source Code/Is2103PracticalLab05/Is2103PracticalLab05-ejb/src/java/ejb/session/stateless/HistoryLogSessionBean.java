package ejb.session.stateless;

import entity.HistoryLog;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;



@Stateless
@Local(HistoryLogSessionBeanLocal.class)

public class HistoryLogSessionBean implements HistoryLogSessionBeanLocal
{
    @PersistenceContext(unitName = "Is2103PracticalLab05-ejbPU")
    private EntityManager em;

    
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @Override
    public HistoryLog createHistoryLogEntry(String logEntry)
    {
        HistoryLog historyLog = new HistoryLog(logEntry);
        em.persist(historyLog);
        
        return historyLog;
    }
    
    
    
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    @Override
    public HistoryLog createHistoryLogEntryRequiresNewTransaction(String logEntry)
    {
        HistoryLog historyLog = new HistoryLog(logEntry);
        em.persist(historyLog);
        
        return historyLog;
    }
}