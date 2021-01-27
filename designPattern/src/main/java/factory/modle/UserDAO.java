package factory.modle;

public interface UserDAO {
    String queryById(String id);
    void insert(String user);
}
