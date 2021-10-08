package com.example.springLearn.newlearn.constom.postprocess;


import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.support.MergedBeanDefinitionPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.stereotype.Component;

@Component
class MyInitializingBean implements InitializingBean {

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("InitializingBean:afterPropertiesSet===============================");
    }
}
