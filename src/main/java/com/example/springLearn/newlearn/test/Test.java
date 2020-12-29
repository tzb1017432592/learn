package com.example.springLearn.newlearn.test;

import com.example.springLearn.newlearn.bean.*;
import com.example.springLearn.newlearn.config.RabbitMqConfig;
import com.example.springLearn.newlearn.config.SpringConfig;
import com.example.springLearn.newlearn.constom.Listener.MyApplicationEven;
import com.example.springLearn.newlearn.controll.TestController;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


import java.util.LinkedList;

import java.util.UUID;

public class Test {
    @org.junit.Test
    public void test1() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfig.class);
        /*  ac.publishEvent(new MyApplicationEven("我发布的事件"));*/
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
        }
    }

    @org.junit.Test
    public void test2() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfig.class);
       /* EsBean bean = ac.getBean(EsBean.class);
        System.out.println(bean);*/
        ac.close();
        //User user=(User) ac.getBean("getuser");
    }


    @org.junit.Test
    public void test22() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfig.class);
        /* ac.publishEvent(new MyApplicationEven("我发布的事件"));*/
        Hbase bean = ac.getBean(Hbase.class);
        System.out.println(bean.toString());
        ac.close();

    }

    @org.junit.Test
    public void test23() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfig.class);
        /*  ac.publishEvent(new MyApplicationEven("我发布的事件"));*/
        ac.close();

    }

    @org.junit.Test
    public void test3() {
        LinkedList<Html> htmls = new LinkedList<>();
        int i = 0;
        while (true) {
            htmls.add(new Html(i++, UUID.randomUUID().toString()));
        }
    }

    @org.junit.Test
    public void test4() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(RabbitMqConfig.class);
        //   RabbitMq bean = ac.getBean(RabbitMq.class);
        Object bean = ac.getBean("&rabbitMq");
        System.out.println(bean.toString());
        ac.close();
    }

    @org.junit.Test
    public void test5() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfig.class);
        TestController bean = ac.getBean(TestController.class);
        bean.test2();
        ac.close();
    }
}
