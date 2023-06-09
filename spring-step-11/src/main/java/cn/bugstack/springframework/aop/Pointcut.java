package cn.bugstack.springframework.aop;

public interface Pointcut {

    /**
     * Return the ClassFilter for this pointcut.
     * @return the ClassFilter (never <code>null</code>)
     */
    ClassFilter getClassFilter();

    /**
     * the MethodMatcher for this pointcut.
     * @return the MethodMatcher (never <code>null</code>)
     */
    MethodMatcher getMethodMatcher();
}
