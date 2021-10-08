package com.example.springLearn.newlearn.constom.postprocess;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @author tianzhoubing
 * @date 2021/6/24 10:33
 * @description
 **/
@Component
public class Inintion implements InstantiationAwareBeanPostProcessor {
    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        System.out.println("InstantiationAwareBeanPostProcessor:postProcessBeforeInstantiation");
        System.out.println(beanName+"开始实例化");
        return null;
    }

    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        System.out.println("InstantiationAwareBeanPostProcessor:postProcessAfterInstantiation");
        System.out.println(beanName+"实例化完成");
        return false;
    }

    @Override
    public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
        System.out.println("InstantiationAwareBeanPostProcessor:postProcessProperties");
        System.out.println(beanName+":postProcessProperties");
        return null;
    }
}
