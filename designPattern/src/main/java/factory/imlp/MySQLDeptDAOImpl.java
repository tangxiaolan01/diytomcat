package factory.imlp;

import factory.modle.DeptDAO;

public class MySQLDeptDAOImpl implements DeptDAO {
    volatile private static MySQLDeptDAOImpl mySQLDeptDAO;

    private  MySQLDeptDAOImpl(){

    }

    public static  MySQLDeptDAOImpl getMySQLDeptDAO(){
        if(mySQLDeptDAO == null){
            synchronized (MySQLDeptDAOImpl.class){
                if(mySQLDeptDAO == null){
                    return new MySQLDeptDAOImpl();
                }
            }
        }
        return mySQLDeptDAO;
    }
    public String queryById(String id) {
        System.out.println("在MySQL中查询到一条Dept数据");
        return null;
    }

    public void insert(String user) {
        System.out.println("在MySQL中插入一条Dept数据");

    }
}
