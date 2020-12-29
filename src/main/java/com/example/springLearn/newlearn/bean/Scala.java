package com.example.springLearn.newlearn.bean;

import lombok.Data;

@Data
public class Scala {
    private int id;
    private String name;

    public Scala() {
        System.out.println("scala的空参构造函数执行..........");
    }

    public void init() {
        System.out.println("init........");
    }

    public void die() {
        System.out.println("destory........");
    }

}
