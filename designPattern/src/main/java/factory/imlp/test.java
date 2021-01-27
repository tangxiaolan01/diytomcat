package factory.imlp;

import factory.modle.DeptDAO;
import factory.modle.UserDAO;

public class test {
    public static void main(String args[]){
        DeptDAO deptDAO = DAOFactory.createDept();
        UserDAO userDAO = DAOFactory.createUser();
        deptDAO.insert("2333434");
        deptDAO.queryById("22");
        userDAO.insert("1232323");
    }
}
