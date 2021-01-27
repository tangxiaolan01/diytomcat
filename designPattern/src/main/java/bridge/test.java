package bridge;

import java.math.BigDecimal;

public class test {
    public static void main(String args[]){
        WxPay wxPay = new WxPay(new PayFaceMode());
        wxPay.transfer("weixin_1092033111","100000109893",new BigDecimal(100));


        ZfbPay zfbPay = new ZfbPay(new PayFingerprintMode());
        zfbPay.transfer("zfb_1092033111","100000109893",new BigDecimal(121));
    }
}
