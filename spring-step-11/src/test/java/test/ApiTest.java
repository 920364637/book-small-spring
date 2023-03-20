package test;

import cn.bugstack.springframework.aop.AdvisedSupport;
import cn.bugstack.springframework.aop.MethodMatcher;
import cn.bugstack.springframework.aop.TargetSource;
import cn.bugstack.springframework.aop.aspectj.AspectJExpressionPointcut;
import cn.bugstack.springframework.aop.framework.AopProxy;
import cn.bugstack.springframework.aop.framework.Cglib2AopProxy;
import cn.bugstack.springframework.aop.framework.JdkDynamicAopProxy;
import cn.bugstack.springframework.aop.framework.ReflectiveMethodInvocation;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.junit.Test;
import test.bean.IUserService;
import test.bean.UserService;
import test.bean.UserServiceInterceptor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ApiTest {
    @Test
    public void test_aop() throws NoSuchMethodException {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut("execution(* test.bean.UserService.*(..))");

        Class<UserService> clazz = UserService.class;

        Method method = clazz.getDeclaredMethod("queryUserInfo");

        System.out.println(pointcut.matches(clazz));
        System.out.println(pointcut.matches(method, clazz));
    }

    @Test
    public void test_dynamic() {
        IUserService service = new UserService();

        AdvisedSupport advisedSupport = new AdvisedSupport();
        advisedSupport.setMethodInterceptor(new UserServiceInterceptor());
        advisedSupport.setTargetSource(new TargetSource(service));
        advisedSupport.setMethodMatcher(new AspectJExpressionPointcut("execution(* test.bean.IUserService.register(..))"));

        IUserService proxy_jdk = (IUserService) new JdkDynamicAopProxy(advisedSupport).getProxy();
        System.out.println(proxy_jdk.queryUserInfo());

        IUserService proxy_cglib = (IUserService) new Cglib2AopProxy(advisedSupport).getProxy();
        System.out.println(proxy_cglib.register());
    }

    private static class MyInvocationHandler implements InvocationHandler {
        private Object object;

        public MyInvocationHandler(Object object) {
            this.object = object;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            Object res = method.invoke(object, args);
            System.out.println(proxy.getClass());
            return res;
        }
    }


    @Test
    public void test_proxy_class() {
        UserService service = new UserService();
        Object proxy_object = Proxy.newProxyInstance(
                Thread.currentThread().getContextClassLoader(),
                new Class[]{IUserService.class},
                new MyInvocationHandler(service)
        );
        System.out.println(proxy_object.getClass());
        System.out.println(((IUserService) proxy_object).register());
    }

    public void test_proxy_method() {
        Object targetObj = new UserService();

        IUserService proxy = (
                IUserService) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                new Class[]{IUserService.class},

                new InvocationHandler() {
                    MethodMatcher methodMatcher = new AspectJExpressionPointcut("execution(* test.bean.IUserService.*(..))");

                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                        if (methodMatcher.matches(method, targetObj.getClass())) {
                            MethodInterceptor methodInterceptor = new MethodInterceptor() {
                                @Override
                                public Object invoke(MethodInvocation methodInvocation) throws Throwable {

                                    long start = System.currentTimeMillis();
                                    try {
                                        return methodInvocation.proceed();
                                    } finally {
                                        System.out.println("监控 - Begin By AOP");
                                        System.out.println("方法名称：" + methodInvocation.getMethod().getName());
                                        System.out.println("方法耗时：" + (System.currentTimeMillis() - start) + "ms");
                                        System.out.println("监控 - End\r\n");
                                    }
                                }
                            };

                            return methodInterceptor.invoke(new ReflectiveMethodInvocation(targetObj, method, args));

                        }

                        return method.invoke(targetObj, args);
                    }

                });
    }
}
