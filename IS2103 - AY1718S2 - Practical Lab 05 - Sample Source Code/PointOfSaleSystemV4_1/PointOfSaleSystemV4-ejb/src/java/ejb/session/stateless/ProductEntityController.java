package ejb.session.stateless;

import entity.ProductEntity;
import java.util.List;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import util.exception.ProductInsufficientQuantityOnHandException;
import util.exception.ProductNotFoundException;



@Stateless
@Local(ProductEntityControllerLocal.class)
@Remote(ProductEntityControllerRemote.class)

public class ProductEntityController implements ProductEntityControllerLocal, ProductEntityControllerRemote
{
    @PersistenceContext(unitName = "PointOfSaleSystemV4-ejbPU")
    private javax.persistence.EntityManager entityManager;
    
    
    
    public ProductEntityController()
    {
    }
    
    
    
    @Override
    public ProductEntity createNewProduct(ProductEntity newProductEntity)
    {
        entityManager.persist(newProductEntity);
        entityManager.flush();
        
        return newProductEntity;
    }
    
    
    
    @Override
    public List<ProductEntity> retrieveAllProducts()
    {
        Query query = entityManager.createQuery("SELECT p FROM ProductEntity p ORDER BY p.skuCode ASC");
        
        return query.getResultList();
    }
    
    
    
    @Override
    public ProductEntity retrieveProductByProductId(Long productId) throws ProductNotFoundException
    {
        ProductEntity productEntity = entityManager.find(ProductEntity.class, productId);
        
        if(productEntity != null)
        {
            return productEntity;
        }
        else
        {
            throw new ProductNotFoundException("Product ID " + productId + " does not exist!");
        }               
    }
    
    
    
    @Override
    public ProductEntity retrieveProductByProductSkuCode(String skuCode) throws ProductNotFoundException
    {
        Query query = entityManager.createQuery("SELECT p FROM ProductEntity p WHERE p.skuCode = :inSkuCode");
        query.setParameter("inSkuCode", skuCode);
        
        try
        {
            return (ProductEntity)query.getSingleResult();
        }
        catch(NoResultException | NonUniqueResultException ex)
        {
            throw new ProductNotFoundException("Sku Code " + skuCode + " does not exist!");
        }
    }
    
    
    
    @Override
    public void updateProduct(ProductEntity productEntity)
    {
        throw new UnsupportedOperationException();
    }
    
    
    
    @Override
    public void deleteProduct(ProductEntity productEntity)
    {
        throw new UnsupportedOperationException();
    }
    
    
    
    @Override
    public void debitQuantityOnHand(Long productId, Integer quantityToDebit) throws ProductNotFoundException, ProductInsufficientQuantityOnHandException
    {
        ProductEntity productEntity = retrieveProductByProductId(productId);
        
        if(productEntity.getQuantityOnHand() >= quantityToDebit)
        {
            productEntity.setQuantityOnHand(productEntity.getQuantityOnHand() - quantityToDebit);
        }
        else
        {
            throw new ProductInsufficientQuantityOnHandException("Product " + productEntity.getSkuCode() + " quantity on hand is " + productEntity.getQuantityOnHand() + " versus quantity to debit of " + quantityToDebit);
        }
    }
    
    
    
    @Override
    public void creditQuantityOnHand(Long productId, Integer quantityToCredit) throws ProductNotFoundException
    {
        ProductEntity productEntity = retrieveProductByProductId(productId);
        productEntity.setQuantityOnHand(productEntity.getQuantityOnHand() + quantityToCredit);
    }
}