package adapter;

import adapter.impl.MQAdapter;
import adapter.vo.CreateAccount;
import adapter.vo.OrderMq;
import adapter.vo.RebateInfo;
import com.alibaba.fastjson.JSON;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class test {
    public static void testAdapt() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        CreateAccount create_account = new CreateAccount();
        create_account.setNumber("100001");
        create_account.setAddress("河北省.廊坊市.广阳区.大学里职业技术学院");
        create_account.setAccountDate(new Date().toString());
        create_account.setDesc("在校开户");



        Map<String,String> link = new HashMap<String, String>();
        link.put("userId","number");
        link.put("bizId","number");
        link.put("bizTime","accountDate");
        link.put("desc","desc");
        RebateInfo rebateInfo01 =  MQAdapter.filter(JSON.toJSONString(create_account),link);
        System.out.println("mq.create_account(适配前)" + create_account.toString());
        System.out.println("mq.create_account(适配后)" + JSON.toJSONString(rebateInfo01));

        System.out.println("");

        OrderMq orderMq = new OrderMq();
        orderMq.setUid("100001");
        orderMq.setSku("10928092093111123");
        orderMq.setOrderId("100000890193847111");
        orderMq.setCreateOrderTime(new Date().toString());
        Map<String,String> link2 = new HashMap<String, String>();
        link2.put("userId","uid");
        link2.put("bizId","orderId");
        link2.put("bizTime","createOrderTime");
        RebateInfo rebateInfo02 =  MQAdapter.filter(JSON.toJSONString(orderMq),link2);
        System.out.println("mq.OrderMq(适配前)" + orderMq.toString());
        System.out.println("mq.OrderMq(适配后)" + JSON.toJSONString(rebateInfo02));

    }


    public static void main(String args[]){
        try {
            testAdapt();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
