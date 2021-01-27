package adapter.impl;

import adapter.OrderAdapterService;
import adapter.OrderService;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class InsideOrderServiceImpl implements OrderAdapterService {

    /*
    通过适配器模式 接口已经做了统一的包装，外部使用时候就不需要关心内部的具体逻辑了。
    而且在调用的时候只需要传入统一的参数即可，这样就满足了适配的作用。
     */
    private OrderService service = new OrderService();
    public boolean isFirst(String useId) {
        return service.queryUserOrderCount(useId)<1;
    }

    public String getUseId(String str){
        Map<String,String> link = new HashMap<String, String>();
        link.put("userId","number");
        link.put("bizId","number");
        link.put("bizTime","accountDate");
        link.put("desc","desc");

        try {
            return  MQAdapter.filter(str,link).getBizId();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
}
