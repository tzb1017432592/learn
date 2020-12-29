package com.example.springLearn.newlearn.bean;

import lombok.Data;

@Data
public class Cat {
    private int id;
    private String name;

    public Cat() {
    }

    public Cat(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
