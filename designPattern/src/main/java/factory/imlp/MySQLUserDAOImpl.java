package factory.imlp;

import factory.modle.UserDAO;

public class MySQLUserDAOImpl implements UserDAO {
    volatile  private static MySQLUserDAOImpl mySQLUserDAO ;
    private MySQLUserDAOImpl(){

    }

    public static  MySQLUserDAOImpl getMySQLUserDAO(){
        if(mySQLUserDAO == null){
            synchronized (MySQLUserDAOImpl.class){
                if (mySQLUserDAO == null)
                    return new MySQLUserDAOImpl();
            }
        }
        return mySQLUserDAO;
    }

    public String queryById(String id) {
        System.out.println("在MySQL中查询到一条Dept数据");
        return null;
    }

    public void insert(String user) {
        System.out.println("在MySQL中插入一条Dept数据");
    }
}
