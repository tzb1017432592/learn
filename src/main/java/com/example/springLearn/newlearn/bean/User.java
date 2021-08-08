package com.example.springLearn.newlearn.bean;

import lombok.Data;
import lombok.SneakyThrows;

import java.io.Closeable;
import java.io.IOException;

@Data
public class User implements Cloneable {
    private int id;
    private String name;

    public User() {
    }

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
