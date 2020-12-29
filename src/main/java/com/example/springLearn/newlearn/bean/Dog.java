package com.example.springLearn.newlearn.bean;

import lombok.Data;

@Data
public class Dog {
    private Integer id;
    private String name;

    public Dog(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Dog() {
    }
}
