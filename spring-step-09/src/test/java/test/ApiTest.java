package test;


import cn.bugstack.springframework.context.support.ClassPathXmlApplicationContext;
import cn.bugstack.springframework.core.io.DefaultResourceLoader;
import cn.bugstack.springframework.core.io.Resource;
import cn.bugstack.springframework.core.io.ResourceLoader;
import cn.bugstack.springframework.beans.factory.support.BeanDefinitionReader;
import cn.bugstack.springframework.beans.factory.support.DefaultListableBeanFactory;
import cn.bugstack.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import cn.hutool.core.io.IoUtil;
import org.junit.Before;
import org.junit.Test;
import org.openjdk.jol.info.ClassLayout;
import test.bean.UserService;

import java.io.IOException;
import java.io.InputStream;


public class ApiTest {
    @Test
    public void test_prototype() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring.xml");
        context.registerShutdownHook();
        UserService service1 = context.getBean("userService", UserService.class);
        UserService service2 = context.getBean("userService", UserService.class);

        System.out.println(service1);
        System.out.println(service2);

        System.out.println(service1 + "十六进制哈希值：" + Integer.toHexString(service1.hashCode()));
        System.out.println(ClassLayout.parseInstance(service1).toPrintable());
    }

    @Test
    public void test_factory_bean(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring.xml");
        context.registerShutdownHook();
        UserService service = context.getBean("userService", UserService.class);
        System.out.println(service.queryUserInfo());
    }


}
