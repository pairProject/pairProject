package ejb.session.stateless;

import entity.SaleTransactionEntity;
import java.util.List;
import util.exception.CreateNewSaleTransactionException;
import util.exception.SaleTransactionAlreadyVoidedRefundedException;
import util.exception.SaleTransactionNotFoundException;



public interface SaleTransactionEntityControllerLocal
{
    SaleTransactionEntity createNewSaleTransaction(SaleTransactionEntity newSaleTransactionEntity) throws CreateNewSaleTransactionException;

    List<SaleTransactionEntity> retrieveAllSaleTransactions();

    SaleTransactionEntity retrieveSaleTransactionBySaleTransactionId(Long saleTransactionId) throws SaleTransactionNotFoundException;
    
    void updateSaleTransaction(SaleTransactionEntity saleTransactionEntity);

    void voidRefundSaleTransaction(Long saleTransactionId) throws SaleTransactionNotFoundException, SaleTransactionAlreadyVoidedRefundedException;
    
    void deleteSaleTransaction(SaleTransactionEntity saleTransactionEntity);
}
