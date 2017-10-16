package entity;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;



@Entity

public class CheckingAccount implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long checkingAccountId;
    @Column(length = 64, nullable = false)
    private String accountName;
    @Column(nullable = false, precision = 18, scale = 4)
    private BigDecimal balance;

    
    
    public CheckingAccount()
    {
        this.accountName = "";
        this.balance = new BigDecimal("0.0000");
    }

    
    
    public CheckingAccount(String accountName, BigDecimal balance)
    {
        this.accountName = accountName;
        this.balance = balance;
    }
    
    

    
    
    public Long getCheckingAccountId() {
        return checkingAccountId;
    }

    public void setCheckingAccountId(Long checkingAccountId) {
        this.checkingAccountId = checkingAccountId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (checkingAccountId != null ? checkingAccountId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the checkingAccountId fields are not set
        if (!(object instanceof CheckingAccount)) {
            return false;
        }
        CheckingAccount other = (CheckingAccount) object;
        if ((this.checkingAccountId == null && other.checkingAccountId != null) || (this.checkingAccountId != null && !this.checkingAccountId.equals(other.checkingAccountId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.CheckingAccount[ id=" + checkingAccountId + " ]";
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