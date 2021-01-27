package strategy;

public class threeStrategy implements strategy {
    @Override
    public double cal(double type) {
        return type * 1.24;
    }
}
