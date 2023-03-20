package cn.bugstack.springframework.context;

public interface ApplicationListener<E extends ApplicationEvent> {
    void onApplicationEvent(E event);
}
