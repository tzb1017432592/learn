package com.example.springLearn.newlearn.bean;

import lombok.Data;

@Data
public class EsBean {
    private int id;
    private String name;

    public EsBean() {
    }

    public EsBean(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
