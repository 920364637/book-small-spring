package test;


import cn.bugstack.springframework.core.io.DefaultResourceLoader;
import cn.bugstack.springframework.core.io.Resource;
import cn.bugstack.springframework.core.io.ResourceLoader;
import cn.bugstack.springframework.beans.factory.support.BeanDefinitionReader;
import cn.bugstack.springframework.beans.factory.support.DefaultListableBeanFactory;
import cn.bugstack.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import cn.hutool.core.io.IoUtil;
import org.junit.Before;
import org.junit.Test;
import test.bean.UserService;

import java.io.IOException;
import java.io.InputStream;


public class ApiTest {
    private ResourceLoader resourceLoader;

    @Before
    public void init() {
        this.resourceLoader = new DefaultResourceLoader();
    }

    @Test
    public void test_classpath() throws IOException {
        Resource resource = resourceLoader.getResource("classpath:important.properties");
        InputStream is = resource.getInputStream();

        String content = IoUtil.readUtf8(is);
        System.out.println(content);
    }

    @Test
    public void test_file() throws IOException {
        Resource resource = resourceLoader.getResource("src/test/resources/important.properties");
        InputStream is = resource.getInputStream();
        String content = IoUtil.readUtf8(is);
        System.out.println(content);
    }


    @Test
    public void test_url() throws IOException {
        Resource resource = resourceLoader.getResource("https://gitcode.net/fuzhengwei/book-small-spring/-/raw/master/spring-step-05/target/test-classes/important.properties");
        InputStream is = resource.getInputStream();
        String content = IoUtil.readUtf8(is);
        System.out.println(content);
    }

    @Test
    public void test_xml() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        BeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions("classpath:spring.xml");

        UserService service = beanFactory.getBean("userService", UserService.class);
        System.out.println(service.getName());
        System.out.println(service.queryUserName("10002"));
    }
}
