package singleton;

public class Singleton_02 {
    //饿汉模式,启动就创建，但是占用内存资源，并且线程不安全

    private static Singleton_02 singleton_02 = new Singleton_02();

    private Singleton_02(){

    }

    public Singleton_02 getSingleton_02(){
        return singleton_02;
    }
}
