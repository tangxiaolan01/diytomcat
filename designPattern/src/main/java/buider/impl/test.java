package buider.impl;

public class test {
    public static void main(String args[]){
        Builder builder = new Builder();
        System.out.println(builder.leverOne().detail());
        System.out.println(builder.leverTwo().detail());
        System.out.println(builder.leverThree().detail());
}
}
