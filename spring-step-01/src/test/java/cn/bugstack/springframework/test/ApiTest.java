package cn.bugstack.springframework.test;

import cn.bugstack.springframework.BeanDefinition;
import cn.bugstack.springframework.BeanFactory;
import cn.bugstack.springframework.test.bean.UserService;
import org.junit.Test;


public class ApiTest {

    @Test
    public void test_BeanFactory() {
        // 初始化BeanFactory接口
        BeanFactory beanFactory = new BeanFactory();

        // 注册Bean对象
        BeanDefinition beanDefinition = new BeanDefinition(new UserService());
        beanFactory.registerBeanDefinition("userService", beanDefinition);

        // 获取Bean实例
        UserService service = (UserService) beanFactory.getBean("userService");
        service.queryUserInfo();

    }
}
