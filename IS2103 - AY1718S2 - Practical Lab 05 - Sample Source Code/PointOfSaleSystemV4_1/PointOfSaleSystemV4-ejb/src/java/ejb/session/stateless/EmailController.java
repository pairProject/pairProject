package ejb.session.stateless;

import entity.SaleTransactionEntity;
import java.util.concurrent.Future;
import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import util.email.EmailManager;



@Stateless
@Local(EmailControllerLocal.class)
@Remote(EmailControllerRemote.class)

public class EmailController implements EmailControllerLocal, EmailControllerRemote
{
    @Override
    public Boolean emailCheckoutNotificationSync(SaleTransactionEntity saleTransactionEntity, String fromEmailAddress, String toEmailAddress)
    {
        EmailManager emailManager = new EmailManager("<REPLACE_WITH_YOUR_UNIX_USERNAME>", "<REPLACE_WITH_YOUR_UNIX_PASSWORD>");
        Boolean result = emailManager.emailCheckoutNotification(saleTransactionEntity, fromEmailAddress, toEmailAddress);
        
        return result;
    } 
    
    
    
    @Asynchronous
    @Override
    public Future<Boolean> emailCheckoutNotificationAsync(SaleTransactionEntity saleTransactionEntity, String fromEmailAddress, String toEmailAddress) throws InterruptedException
    {
        EmailManager emailManager = new EmailManager("<REPLACE_WITH_YOUR_UNIX_USERNAME>", "<REPLACE_WITH_YOUR_UNIX_PASSWORD>");
        Boolean result = emailManager.emailCheckoutNotification(saleTransactionEntity, fromEmailAddress, toEmailAddress);
        
        return new AsyncResult<>(result);
    } 
}
