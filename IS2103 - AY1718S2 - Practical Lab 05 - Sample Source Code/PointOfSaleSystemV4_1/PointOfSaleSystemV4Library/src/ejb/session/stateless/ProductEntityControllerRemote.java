package ejb.session.stateless;

import entity.ProductEntity;
import java.util.List;
import util.exception.ProductInsufficientQuantityOnHandException;
import util.exception.ProductNotFoundException;



public interface ProductEntityControllerRemote
{
    ProductEntity createNewProduct(ProductEntity newProductEntity);
  
    List<ProductEntity> retrieveAllProducts();

    ProductEntity retrieveProductByProductId(Long productId) throws ProductNotFoundException;

    ProductEntity retrieveProductByProductSkuCode(String skuCode) throws ProductNotFoundException;

    void updateProduct(ProductEntity productEntity);
    
    void deleteProduct(ProductEntity productEntity);
    
    public void debitQuantityOnHand(Long productId, Integer quantityToDebit) throws ProductNotFoundException, ProductInsufficientQuantityOnHandException;
    
    public void creditQuantityOnHand(Long productId, Integer quantityToCredit) throws ProductNotFoundException;
}
