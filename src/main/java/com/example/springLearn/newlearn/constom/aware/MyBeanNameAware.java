package com.example.springLearn.newlearn.constom.aware;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.stereotype.Component;

/**
 * @author tianzhoubing
 * @date 2021/7/1 21:27
 * @description
 **/
@Component
public class MyBeanNameAware implements BeanNameAware {
    @Override
    public void setBeanName(String s) {
        System.out.println("MyBeanNameAware");
        System.out.println(s);
    }
}
