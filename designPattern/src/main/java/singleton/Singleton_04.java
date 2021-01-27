package singleton;


public class Singleton_04 {
    //双重锁，线程安全
    private static Singleton_04 instance = new Singleton_04();

    private Singleton_04(){

    }

    public Singleton_04 getInstance(){
      if(instance != null)
          return instance;

        synchronized(Singleton_04.class){
            if(null == instance){
                instance =   new Singleton_04();
            }
        }
        return instance;
    }
}
