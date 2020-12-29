package com.example.springLearn.newlearn.bean;

import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
public class MySql {
    private String name = "MySql";

    public void say() {
        System.out.println("我是" + name);
    }
}
