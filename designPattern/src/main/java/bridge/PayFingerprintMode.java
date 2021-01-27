package bridge;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PayFingerprintMode  implements IPayMode {
    protected Logger logger = LoggerFactory.getLogger(PayFaceMode.class);

    public boolean security(String uid) {
        logger.info("指纹支付，风控校验脸部识别");
        return true;
    }
}
