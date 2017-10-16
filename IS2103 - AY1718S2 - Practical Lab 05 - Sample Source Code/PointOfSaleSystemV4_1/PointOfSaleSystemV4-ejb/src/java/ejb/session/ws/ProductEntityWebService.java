package ejb.session.ws;

import ejb.session.stateless.ProductEntityControllerLocal;
import ejb.session.stateless.StaffEntityControllerLocal;
import entity.ProductEntity;
import entity.StaffEntity;
import java.util.List;
import javax.ejb.EJB;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.ejb.Stateless;
import util.exception.InvalidLoginCredentialException;



@WebService(serviceName = "ProductEntityWebService")
@Stateless

public class ProductEntityWebService
{
    @EJB
    private StaffEntityControllerLocal staffEntityControllerLocal;
    @EJB
    private ProductEntityControllerLocal productEntityControllerLocal;
    
    
    
    @WebMethod(operationName = "retrieveAllProducts")
    public List<ProductEntity> retrieveAllProducts(@WebParam(name = "username") String username, 
                                                    @WebParam(name = "password") String password) 
                                                    throws InvalidLoginCredentialException
    {
        StaffEntity staffEntity = staffEntityControllerLocal.staffLogin(username, password);
        System.out.println("********** ProductEntityWebService.retrieveAllProducts(): Staff " + staffEntity.getUsername() + " login remotely via web service");
        
        return productEntityControllerLocal.retrieveAllProducts();
    }
}
