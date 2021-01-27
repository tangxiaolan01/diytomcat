package bridge;





import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

public abstract class Pay {
    protected Logger logger = LoggerFactory.getLogger(Pay.class);

    public   IPayMode payMode ;

    public Pay(IPayMode pay) {
        this.payMode = pay;
    }

    public abstract String transfer(String uId, String tradeId, BigDecimal amount);
}
