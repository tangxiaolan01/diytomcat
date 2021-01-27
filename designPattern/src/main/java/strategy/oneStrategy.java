package strategy;

public class oneStrategy implements strategy {
    @Override
    public double cal(double type) {
        return type*0.1;
    }
}
