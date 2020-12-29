package com.example.springLearn.newlearn.constom;

import com.example.springLearn.newlearn.bean.Hbase;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Order(1)
public class MyContextInitializer implements
        ApplicationContextInitializer<ConfigurableApplicationContext> {
    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        ConfigurableListableBeanFactory beanFactory = applicationContext.getBeanFactory();
        beanFactory.registerSingleton("hbase", new Hbase(1, "我是系统初始化器注册的Hbase"));
        Hbase bean = beanFactory.getBean(Hbase.class);
        System.out.println(bean.toString());
        System.out.println("我的系统初始化器==========================");
    }
}
