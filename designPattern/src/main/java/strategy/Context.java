package strategy;

public class Context {
    private strategy s ;
    public double getCal(double charge,int type){
       s =  strategyFactory.getInstance().create(type);
       return s.cal(charge);
    }


}
