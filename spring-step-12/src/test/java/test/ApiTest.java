package test;

import cn.bugstack.springframework.context.support.ClassPathXmlApplicationContext;

import org.junit.Test;
import test.bean.IUserService;


public class ApiTest {
    @Test
    public void test_aop() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        IUserService userService = applicationContext.getBean("userService", IUserService.class);
        System.out.println("测试结果: "+ userService.queryUserInfo());
    }

}


