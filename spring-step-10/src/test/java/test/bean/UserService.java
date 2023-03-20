package test.bean;

import cn.bugstack.springframework.beans.factory.*;
import cn.bugstack.springframework.context.ApplicationContext;
import cn.bugstack.springframework.context.ApplicationContextAware;

public class UserService {

    private String uId;
    private String company;
    private String location;
    private IUserDao userDao;


    public String queryUserInfo() {
        return userDao.queryUserName(uId) + "," + company + "," + location;
    }

}
