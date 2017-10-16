package ejb.session.singleton;

import ejb.session.stateless.ProductEntityControllerLocal;
import ejb.session.stateless.StaffEntityControllerLocal;
import entity.ProductEntity;
import entity.StaffEntity;
import java.math.BigDecimal;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.LocalBean;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import util.enumeration.AccessRightEnum;
import util.exception.StaffNotFoundException;



@Singleton
@LocalBean
@Startup

public class DataInitializationSessionBean
{
    @PersistenceContext(unitName = "PointOfSaleSystemV4-ejbPU")
    private EntityManager entityManager;
    
    @EJB
    private StaffEntityControllerLocal staffEntityControllerLocal;
    @EJB
    private ProductEntityControllerLocal productEntityControllerLocal;
    
    
    
    public DataInitializationSessionBean()
    {
    }
    
    
    
    @PostConstruct
    public void postConstruct()
    {
        try
        {
            staffEntityControllerLocal.retrieveStaffByUsername("manager");
        }
        catch(StaffNotFoundException ex)
        {
            initializeData();
        }
    }
    
    
    
    private void initializeData()
    {
        staffEntityControllerLocal.createNewStaff(new StaffEntity("Default", "Manager", AccessRightEnum.MANAGER, "manager", "password"));
        
        productEntityControllerLocal.createNewProduct(new ProductEntity("PROD001", "Product A1", "Product A1", 10, new BigDecimal("10.00"), "Category A"));
        productEntityControllerLocal.createNewProduct(new ProductEntity("PROD002", "Product A2", "Product A2", 10, new BigDecimal("25.50"), "Category A"));
        productEntityControllerLocal.createNewProduct(new ProductEntity("PROD003", "Product A3", "Product A3", 10, new BigDecimal("15.00"), "Category A"));
        productEntityControllerLocal.createNewProduct(new ProductEntity("PROD004", "Product B1", "Product B1", 10, new BigDecimal("20.00"), "Category B"));
        productEntityControllerLocal.createNewProduct(new ProductEntity("PROD005", "Product B2", "Product B2", 10, new BigDecimal("10.00"), "Category B"));
        productEntityControllerLocal.createNewProduct(new ProductEntity("PROD006", "Product B3", "Product B3", 10, new BigDecimal("100.00"), "Category B"));
        productEntityControllerLocal.createNewProduct(new ProductEntity("PROD007", "Product C1", "Product C1", 10, new BigDecimal("35.00"), "Category C"));
        productEntityControllerLocal.createNewProduct(new ProductEntity("PROD008", "Product C2", "Product C2", 10, new BigDecimal("20.05"), "Category C"));
        productEntityControllerLocal.createNewProduct(new ProductEntity("PROD009", "Product C3", "Product C3", 10, new BigDecimal("5.50"), "Category C"));
    }
}