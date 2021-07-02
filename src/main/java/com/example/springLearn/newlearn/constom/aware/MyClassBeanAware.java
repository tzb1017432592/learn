package com.example.springLearn.newlearn.constom.aware;

import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.stereotype.Component;

/**
 * @author tianzhoubing
 * @date 2021/7/1 21:28
 * @description
 **/
@Component
public class MyClassBeanAware implements BeanClassLoaderAware {
    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        System.out.println("MyClassBeanAware");
        System.out.println(classLoader);
    }
}
