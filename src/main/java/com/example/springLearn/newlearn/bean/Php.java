package com.example.springLearn.newlearn.bean;

import lombok.Data;

@Data
public class Php {
    private int id;
    private String name;

    public Php() {
    }

    public Php(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
