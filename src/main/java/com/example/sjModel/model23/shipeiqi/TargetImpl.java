package com.example.sjModel.model23.shipeiqi;

public class TargetImpl implements Target {
    @Override
    public Integer output5v() {
        System.out.println("这是5V的电压");
        return 5;
    }
}
