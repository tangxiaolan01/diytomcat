package singleton;

public class Singleton_01 {
    //懒汉模式
    private static Singleton_01 singleton_01;

    private Singleton_01(){

    }

    public Singleton_01 getSingleton_01(){
        if(singleton_01 != null){
            return singleton_01;
        }
        return  new Singleton_01();
    }
}
