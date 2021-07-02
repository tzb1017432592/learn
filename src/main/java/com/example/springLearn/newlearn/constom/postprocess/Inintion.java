package com.example.springLearn.newlearn.constom.postprocess;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;

/**
 * @author tianzhoubing
 * @date 2021/6/24 10:33
 * @description
 **/
public class Inintion implements InstantiationAwareBeanPostProcessor {
    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        System.out.println("InstantiationAwareBeanPostProcessor:postProcessBeforeInstantiation");
        System.out.println(beanName);
        return null;
    }

    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        System.out.println("InstantiationAwareBeanPostProcessor:postProcessAfterInstantiation");
        System.out.println(beanName);
        return false;
    }

    @Override
    public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
        System.out.println("InstantiationAwareBeanPostProcessor:postProcessProperties");
        System.out.println(beanName);
        return null;
    }
}
