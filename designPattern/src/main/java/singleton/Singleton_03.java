package singleton;

public class Singleton_03 {
    //使用内部类，这样就是实现饿汉模式和懒加载

    public static class innerClass{
        private static  Singleton_03 singleton_03 = new Singleton_03();
    }

    private Singleton_03(){

    }

    public Singleton_03 singleton_03(){
        return innerClass.singleton_03;
    }
}
