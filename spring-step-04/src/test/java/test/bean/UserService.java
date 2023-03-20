package test.bean;

public class UserService {
    private String name;
    private UserDao userDao;

    public void queryUserInfo(String uId) {
        System.out.println("查询学生信息");
        System.out.println("学号: " + uId + "\t姓名: " + userDao.queryUserInfo(uId));
    }

    public UserService() {
    }

    public UserService(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return "UserService{" +
                "name='" + name + '\'' +
                '}';
    }
}
