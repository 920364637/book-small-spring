package cn.bugstack.springframework.aop;

public interface ClassFilter {

    /**
     * Should the pointcut apply to the given interface or target class
     * @param clazz the candidate target class
     * @return whether the advice should apply to the given class
     */
    boolean matches(Class<?> clazz);
}
