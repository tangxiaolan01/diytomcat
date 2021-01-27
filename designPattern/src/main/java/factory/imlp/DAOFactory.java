package factory.imlp;

import factory.modle.DeptDAO;
import factory.modle.UserDAO;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Properties;

public class DAOFactory {

    private static String dataBase;

    static {
        try{
            Properties properties = new Properties();
            InputStream inputStream = DAOFactory.class.getClassLoader()
                    .getResourceAsStream("config.properties");
            properties.load(inputStream);
            dataBase = properties.getProperty("databaseName");
        }catch (IOException e){
            System.out.println(e);
        }
    }


    public static UserDAO createUser(){
        String className = "factory.imlp." + dataBase + "UserDAOImpl";
        try{
            Class<UserDAO> userDAOClass = (Class<UserDAO>) Class.forName(className);
           Method method =  userDAOClass.getMethod("getMySQLUserDAO");
           return (UserDAO) method.invoke(userDAOClass);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static DeptDAO createDept(){
        String className = "factory.imlp." + dataBase + "DeptDAOImpl";
        try{
            Class<DeptDAO> deptDAOClass  = (Class<DeptDAO>) Class.forName(className);
            Method method =  deptDAOClass .getMethod("getMySQLDeptDAO");
            return (DeptDAO) method.invoke(deptDAOClass);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
