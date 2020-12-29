package com.example.springLearn.newlearn.bean;

import lombok.Data;

@Data
public class Hadoop {
    private int id;
    private String name;

    public Hadoop() {
    }

    public Hadoop(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
