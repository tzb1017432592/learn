package com.example.springLearn.newlearn.bean;

import lombok.Data;

@Data
public class Hbase {
    private int id;
    private String name;

    public Hbase(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Hbase() {
    }
}
