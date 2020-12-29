package com.example.reflectLearn.bean;

import lombok.Data;

@Data
public class Dog {
    private String name;
    private String sex;

    public Dog() {
    }

    public Dog(String name, String sex) {
        this.name = name;
        this.sex = sex;
    }
}
