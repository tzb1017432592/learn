package com.example.springLearn.bean;

import lombok.Data;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Data
public class Dog implements InitializingBean, DisposableBean {

    private int id;
    private String name;

    @Value("hashiqi")
    private String type;
    @Value("yulin")
    private String city;

    public Dog(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Dog() {
        System.out.println("我是初始化方法-----------------");
    }

    public void init() {
        System.out.println("init........");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("我销毁了--------------------------");
    }

    public void die() {
        System.out.println("destory........");
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("我被创建了-----------------------");
    }

    //对象被创建并初始化的时候调用
    @PostConstruct
    public void postCoustruct() {
        System.out.println("@PostConstruct---对象被创建并初始化的时候调用");
    }

    @PreDestroy
    public void predistory() {
        System.out.println("@PreDestroy---容器移除对象之前被调用");
    }
}
