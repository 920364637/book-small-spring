package test;


import cn.bugstack.springframework.beans.factory.BeanFactory;
import cn.bugstack.springframework.beans.factory.config.BeanDefinition;
import cn.bugstack.springframework.beans.factory.support.DefaultListableBeanFactory;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;
import org.junit.Test;
import test.bean.UserService;

import java.lang.reflect.Constructor;


public class ApiTest {

    @Test
    public void test_BeanFactory() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
        beanFactory.registerBeanDefinition("userService", beanDefinition);
        beanFactory.registerBeanDefinition("userService2", beanDefinition);

        UserService userService = (UserService) beanFactory.getBean("userService", "Jackson");
        System.out.println(userService);

        // 无参类型应该引发报错
//        UserService userService2 = (UserService) beanFactory.getBean("userService2");
//        System.out.println(userService2);
    }

    @Test
    public void test_newInstance() throws IllegalAccessException, InstantiationException {
        // 无参
        UserService service = UserService.class.newInstance();
        System.out.println(service);
    }

    @Test
    public void test_constructor() throws Exception {
        Constructor<UserService> ctor = UserService.class.getDeclaredConstructor(String.class);
        UserService service = ctor.newInstance("Jackson");
        System.out.println(service);
    }

    @Test
    public void test_parameterTypes() throws Exception {
        Constructor<?>[] ctors = UserService.class.getDeclaredConstructors();
        for (Constructor<?> ctor : ctors) {
            System.out.println(ctor);
        }
        UserService s1 = (UserService) ctors[0].newInstance("Jackson");
        UserService s2 = (UserService) ctors[1].newInstance();
        System.out.println(s1);
        System.out.println(s2);


        s1 = UserService.class.getDeclaredConstructor().newInstance();
        s2 = UserService.class.getDeclaredConstructor(String.class).newInstance("Jackson2");
        System.out.println(s1);
        System.out.println(s2);
    }

    @Test
    public void test_cglib() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(UserService.class);
        enhancer.setCallback(new NoOp() {
            @Override
            public int hashCode() {
                return super.hashCode();
            }
        });
        UserService s1 = (UserService) enhancer.create();
        UserService s2 = (UserService) enhancer.create(new Class[]{String.class}, new Object[]{"Jackson"});
        System.out.println(s1);
        System.out.println(s2);
    }

}
