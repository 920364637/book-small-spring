package cn.bugstack.springframework.aop;

import java.lang.reflect.Method;

public interface MethodMatcher {

    /**
     * Perform static checking whether the given method matchers. If this
     * @param method
     * @param targetClass
     * @return
     */
    boolean matches(Method method, Class<?> targetClass);
}
