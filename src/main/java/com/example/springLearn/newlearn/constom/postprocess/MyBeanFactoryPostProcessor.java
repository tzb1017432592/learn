package com.example.springLearn.newlearn.constom.postprocess;

import com.example.springLearn.newlearn.bean.MySql;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

@Component
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Autowired
    private MySql mySql;

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("开始执行我们的BeanFactoryPostProcessor=============");
        //此时的mySql没有被创建,调用beanFactory.getBean才会被创建
        if (mySql == null) {
            System.out.println("BeanFactory现在只是保存了bean消息，没有创建对象");
        } else {
            System.out.println("BeanFactory现在即保存了bean消息，也创建了对象");
        }

        Object mySql2 = beanFactory.getBean("mySql");
        if (mySql2 == null) {
            System.out.println("调用beanFactory.getBean才会被创建后，BeanFactory现在只是保存了bean消息，没有创建对象");
        } else {
            BeanDefinition mysql = beanFactory.getBeanDefinition("mySql");
            MutablePropertyValues propertyValues = mysql.getPropertyValues();
            propertyValues.add("name", "我是后置处理器的mysql");
            System.out.println("调用beanFactory.getBean才会被创建后，BeanFactory现在即保存了bean消息，也创建了对象");
        }
    }
}
