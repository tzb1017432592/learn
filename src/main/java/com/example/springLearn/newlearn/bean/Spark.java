package com.example.springLearn.newlearn.bean;

import lombok.Data;

@Data
public class Spark {
    private Integer id;
    private String name;

    public Spark() {
    }

    public Spark(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}
