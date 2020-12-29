package com.example.springLearn.newlearn.constom;

import com.example.springLearn.newlearn.bean.Dog;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.lang.reflect.Field;


public class ConditionConstom implements Condition {
    /**
     * @param conditionContext：判断上下文环境
     * @param annotatedTypeMetadata
     * @return
     */
    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        //获取当前环境信息
        Environment environment = conditionContext.getEnvironment();
        //获取ioc的创建工厂
        ConfigurableListableBeanFactory beanFactory = conditionContext.getBeanFactory();
        //获取注册类
        BeanDefinitionRegistry registry = conditionContext.getRegistry();

        if (registry.containsBeanDefinition("getuser")) {
            Class<Dog> dogClass = Dog.class;
            Field[] declaredFields = dogClass.getDeclaredFields();
            MutablePropertyValues mv = new MutablePropertyValues();
            for (Field declaredField : declaredFields) {
                if (declaredField.getName().equals("name")) {
                    mv.add(declaredField.getName(), "conditionDogname");
                }
                if (declaredField.getName().equals("id")) {
                    mv.add(declaredField.getName(), 1);
                }
            }
            BeanDefinition beanDefinition = new RootBeanDefinition(Dog.class, null, mv);
            registry.registerBeanDefinition("conditionDog", beanDefinition);
            Dog dogbean = (Dog) beanFactory.getBean("conditionDog");
            System.out.println("我注册了conditionDog,");
            System.out.println(dogbean.toString());
            return true;
        }
        return false;
    }
}
