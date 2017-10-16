package entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;



@Entity

public class HistoryLog implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long historyLogId;
    @Column(length = 2048, nullable = false)
    private String logEntry;

    
    
    public HistoryLog() 
    {
    }

    
    
    public HistoryLog(String logEntry) 
    {
        this.logEntry = logEntry;
    }
    
    
    
    public Long getHistoryLogId() {
        return historyLogId;
    }

    public void setHistoryLogId(Long historyLogId) {
        this.historyLogId = historyLogId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (historyLogId != null ? historyLogId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the historyLogId fields are not set
        if (!(object instanceof HistoryLog)) {
            return false;
        }
        HistoryLog other = (HistoryLog) object;
        if ((this.historyLogId == null && other.historyLogId != null) || (this.historyLogId != null && !this.historyLogId.equals(other.historyLogId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.HistoryLog[ id=" + historyLogId + " ]";
    }
}