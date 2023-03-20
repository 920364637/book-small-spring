package cn.bugstack.springframework.beans;


public class BeansException extends RuntimeException {
    public BeansException(String msg) {
        super(msg);
    }

    public BeansException(String msg, Exception e) {
        super(msg, e);
    }
}
