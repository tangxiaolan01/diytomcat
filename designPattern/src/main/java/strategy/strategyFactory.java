package strategy;

import java.util.HashMap;
import java.util.Map;

public class strategyFactory {
    private static strategyFactory factory = new strategyFactory();

    private static Map<Integer,strategy> map = new HashMap<>();

    static {
        map.put(1,new oneStrategy());
        map.put(2,new twoStrategy());
        map.put(3,new threeStrategy());
    }

    private strategyFactory(){

    }

    public static strategy create(int type){
        return map.get(type);
    }

    public static strategyFactory getInstance(){
        return  factory;
    }
}
