package com.example.springLearn.newlearn.constom.postprocess;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class MyBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println(beanName+"开始初始化了.......");
        System.out.println("BeanPostProcessor:postProcessBeforeInitialization");
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
         System.out.println(beanName+"初始化完成了.......");
        System.out.println("BeanPostProcessor:postProcessAfterInitialization");
        return bean;
    }
}
