package com.example.springLearn.newlearn.bean;

import lombok.Data;
import org.junit.Test;
import org.springframework.stereotype.Service;

@Service
@Data
public class Kafka {
    private String name = "kafka";

    public void say() {
        System.out.println("我是" + name);
    }
}
