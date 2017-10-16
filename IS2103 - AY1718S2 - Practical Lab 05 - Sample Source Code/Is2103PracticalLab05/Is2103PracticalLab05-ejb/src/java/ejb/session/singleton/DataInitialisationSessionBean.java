package ejb.session.singleton;

import entity.CheckingAccount;
import entity.SavingsAccount;
import java.math.BigDecimal;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.LocalBean;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;



@Singleton
@LocalBean
@Startup

public class DataInitialisationSessionBean
{
    @PersistenceContext(unitName = "Is2103PracticalLab05-ejbPU")
    private EntityManager em;
 
    
    
    public DataInitialisationSessionBean()
    {
    }
    
    
    
    @PostConstruct
    public void postConstruct()
    {
        if(em.find(CheckingAccount.class, 1l) == null)
        {
            loadTestData();
        }
    }
    
    
    
    private void loadTestData()
    {
        em.persist(new CheckingAccount("Checking Account 1", new BigDecimal("1000.0000")));
        em.persist(new CheckingAccount("Checking Account 2", new BigDecimal("1000.0000")));
        em.persist(new CheckingAccount("Checking Account 3", new BigDecimal("1000.0000")));
        em.persist(new SavingsAccount("Savings Account A", new BigDecimal("0.0000")));
        em.persist(new SavingsAccount("Savings Account B", new BigDecimal("0.0000")));
        em.persist(new SavingsAccount("Savings Account C", new BigDecimal("0.0000")));
    }
}
