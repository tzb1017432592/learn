package com.example.springLearn.config;

import com.example.springLearn.bean.Dog;
import com.example.springLearn.bean.Person;
import com.example.springLearn.bean.ServiceTest;
import com.example.springLearn.custom.MyFilter;
import org.junit.Test;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Controller;

@Configuration
@ComponentScan(excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {SpringBootApplication.class, Controller.class}),
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {ServiceTest.class}),
        @ComponentScan.Filter(type = FilterType.CUSTOM, classes = {MyFilter.class})
}, value = "com.example.springLearn", useDefaultFilters = false)
//excludeFilters 排除
//includeFilters 只包含，要设置useDefaultFilters=false
//FilterType.ANNOTATION按照注解排除,FilterType.ASSIGNABLE_TYPE按照类排除
public class Config {
    @Scope()
    @Bean
    public Person person() {
        Person person = new Person();
        person.setName("dsdsd");
        person.setSex("nan");
        System.out.println("我被调用了-----------------------");
        return person;
    }

    @Bean(initMethod = "init", destroyMethod = "die")
    public Dog dog() {
        return new Dog();
    }

    @Bean
    public Dog dog2() {
        return new Dog(1, "dog2");
    }


    @Test
    public void test1() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config.class);
        // Object person = applicationContext.getBean(Person.class);
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
        }
        // System.out.println(person);
    }

    @Test
    public void test2() {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(Config.class);
        String[] beanDefinitionNames = annotationConfigApplicationContext.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
        }
        annotationConfigApplicationContext.close();
    }

    @Test
    public void test3() {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(Config.class);
        Dog dog = (Dog) annotationConfigApplicationContext.getBean("dog2");
        System.out.println(dog.toString());
        annotationConfigApplicationContext.close();
    }
}
