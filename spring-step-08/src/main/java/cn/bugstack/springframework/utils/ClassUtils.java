package cn.bugstack.springframework.utils;

public class ClassUtils {

    public static ClassLoader getDefaultClassLoader() {
        ClassLoader cl = null;
        try {
            cl = Thread.currentThread().getContextClassLoader();
        } catch (Throwable ex) {
        }

        if (cl == null) {
            cl = ClassUtils.getDefaultClassLoader();
        }
        return cl;
    }


}
