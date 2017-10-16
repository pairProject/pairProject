package ejb.session.stateful;

import entity.ProductEntity;
import entity.SaleTransactionEntity;
import entity.SaleTransactionLineItemEntity;
import entity.StaffEntity;
import java.math.BigDecimal;
import java.util.List;
import util.exception.CreateNewSaleTransactionException;



public interface CheckoutControllerLocal
{

    BigDecimal addItem(ProductEntity productEntity, Integer quantity);
    
    SaleTransactionEntity doCheckout(StaffEntity staffEntity) throws CreateNewSaleTransactionException;

    void clearShoppingCart();

    

    List<SaleTransactionLineItemEntity> getSaleTransactionLineItemEntities();
    
    void setSaleTransactionLineItemEntities(List<SaleTransactionLineItemEntity> saleTransactionLineItemEntities);

    Integer getTotalLineItem();
    
    void setTotalLineItem(Integer totalLineItem);
    
    Integer getTotalQuantity();
    
    void setTotalQuantity(Integer totalQuantity);
    
    BigDecimal getTotalAmount();

    void setTotalAmount(BigDecimal totalAmount);
}
