/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.ws;

import ejb.session.stateless.SaleTransactionEntityControllerLocal;
import entity.SaleTransactionEntity;
import java.util.List;
import javax.ejb.EJB;
import javax.jws.WebService;
import javax.ejb.Stateless;
import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import util.exception.CreateNewSaleTransactionException;
import util.exception.SaleTransactionAlreadyVoidedRefundedException;
import util.exception.SaleTransactionNotFoundException;

/**
 *
 * @author Samango
 */
@WebService(serviceName = "SaleTransactionEntityWebService")
@Stateless()
public class SaleTransactionEntityWebService {

    @EJB
    private SaleTransactionEntityControllerLocal ejbRef;// Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Web Service Operation")

    @WebMethod(operationName = "createNewSaleTransaction")
    public SaleTransactionEntity createNewSaleTransaction(@WebParam(name = "newSaleTransactionEntity") SaleTransactionEntity newSaleTransactionEntity) throws CreateNewSaleTransactionException {
        return ejbRef.createNewSaleTransaction(newSaleTransactionEntity);
    }

    @WebMethod(operationName = "retrieveAllSaleTransactions")
    public List<SaleTransactionEntity> retrieveAllSaleTransactions() {
        return ejbRef.retrieveAllSaleTransactions();
    }

    @WebMethod(operationName = "retrieveSaleTransactionBySaleTransactionId")
    public SaleTransactionEntity retrieveSaleTransactionBySaleTransactionId(@WebParam(name = "saleTransactionId") Long saleTransactionId) throws SaleTransactionNotFoundException {
        return ejbRef.retrieveSaleTransactionBySaleTransactionId(saleTransactionId);
    }

    @WebMethod(operationName = "updateSaleTransaction")
    @Oneway
    public void updateSaleTransaction(@WebParam(name = "saleTransactionEntity") SaleTransactionEntity saleTransactionEntity) {
        ejbRef.updateSaleTransaction(saleTransactionEntity);
    }

    @WebMethod(operationName = "voidRefundSaleTransaction")
    public void voidRefundSaleTransaction(@WebParam(name = "saleTransactionId") Long saleTransactionId) throws SaleTransactionNotFoundException, SaleTransactionAlreadyVoidedRefundedException {
        ejbRef.voidRefundSaleTransaction(saleTransactionId);
    }

    @WebMethod(operationName = "deleteSaleTransaction")
    @Oneway
    public void deleteSaleTransaction(@WebParam(name = "saleTransactionEntity") SaleTransactionEntity saleTransactionEntity) {
        ejbRef.deleteSaleTransaction(saleTransactionEntity);
    }
    
}
