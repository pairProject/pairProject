package ejb.session.stateless;

import entity.HistoryLog;



public interface HistoryLogSessionBeanLocal
{
    public HistoryLog createHistoryLogEntry(String logEntry);
    
    public HistoryLog createHistoryLogEntryRequiresNewTransaction(String logEntry);
}