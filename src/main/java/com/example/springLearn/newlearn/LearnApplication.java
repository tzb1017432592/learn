package com.example.springLearn.newlearn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
public class LearnApplication {
    public static void main(String[] args) {
        SpringApplication.run(LearnApplication.class, args);

    }

}
