package ejb.session.stateless;

import java.math.BigDecimal;
import util.exception.EncashCheckException;



public interface CheckProcessingSessionBeanLocal
{
    public void encashCheck(Long checkingAccountId, Long savinngsAccountId, BigDecimal amount) throws EncashCheckException;
    
    public void encashCheckRequiresNewTransaction(Long checkingAccountId, Long savinngsAccountId, BigDecimal amount) throws EncashCheckException;
}