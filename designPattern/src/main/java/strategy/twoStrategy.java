package strategy;

public class twoStrategy implements strategy {
    @Override
    public double cal(double type) {
        return type * 0.25;
    }
}
