package cn.bugstack.springframework.beans.factory.config;

import cn.bugstack.springframework.beans.BeansException;

public interface InstantiationAwareBeanPostProcessor extends BeanPostProcessor {
    Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName);
}
