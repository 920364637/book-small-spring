package test.bean;

public class UserService {
    private String name;

    public void queryUserInfo() {
        System.out.println("查询学生信息");
    }
    public UserService(){}
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
