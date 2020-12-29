package com.example.springLearn.newlearn.bean;

import lombok.Data;

@Data
public class Hive {
    private Integer id;
    private String name;

    public Hive() {
    }

    public Hive(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}
