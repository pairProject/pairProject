package entity;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;



@Entity

public class SavingsAccount implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long savingsAccountId;
    @Column(length = 64, nullable = false)
    private String accountName;
    @Column(nullable = false, precision = 18, scale = 4)
    private BigDecimal balance;

    
    
    public SavingsAccount() 
    {
        this.accountName = "";
        this.balance = new BigDecimal("0.0000");
    }

    
    
    public SavingsAccount(String accountName, BigDecimal balance)
    {
        this.accountName = accountName;
        this.balance = balance;
    }
    
    
    
    public Long getSavingsAccountId() {
        return savingsAccountId;
    }

    public void setSavingsAccountId(Long savingsAccountId) {
        this.savingsAccountId = savingsAccountId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (savingsAccountId != null ? savingsAccountId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the savingsAccountId fields are not set
        if (!(object instanceof SavingsAccount)) {
            return false;
        }
        SavingsAccount other = (SavingsAccount) object;
        if ((this.savingsAccountId == null && other.savingsAccountId != null) || (this.savingsAccountId != null && !this.savingsAccountId.equals(other.savingsAccountId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.SavingsAccount[ id=" + savingsAccountId + " ]";
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}