package com.example.springLearn.service;

import java.util.Random;


public class AopService {
    public Integer testAop() {
        Random random = new Random();
        int i = random.nextInt(5000);
        System.out.println(i);
        return i;
    }
}
