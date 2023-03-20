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
import test.event.CustomEvent;

import java.io.IOException;
import java.io.InputStream;


public class ApiTest {
    @Test
    public void test_event() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring.xml");
        context.publishEvent(new CustomEvent(context, 1001L, "成功了"));
        context.registerShutdownHook();
    }

}
