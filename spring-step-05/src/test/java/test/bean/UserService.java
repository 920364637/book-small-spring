package test.bean;

public class UserService {


    private String name;
    private UserDao userDao;

    public String queryUserName(String uId) {
        return userDao.queryUserInfo(uId);
    }

    public UserService() {
    }

    public UserService(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "UserService{" +
                "name='" + name + '\'' +
                '}';
    }
}
