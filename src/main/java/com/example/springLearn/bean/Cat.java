package com.example.springLearn.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Cat {
    private String name;
    private int age;


    public void die() {
        System.out.println("我死了。。。。。。。");
    }

    public void live() {
        System.out.println("我出生了。。。。。。");
    }
}
