package cn.bugstack.springframework.beans.factory;

public interface InitializingBean {

    /**
     * 在Bean对象填充属性后调用
     * @throws Exception
     */
    void afterPropertiesSet() throws Exception;
}
