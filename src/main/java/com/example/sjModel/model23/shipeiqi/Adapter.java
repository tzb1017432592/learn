package com.example.sjModel.model23.shipeiqi;

public class Adapter extends Adaptee implements Target {
    @Override
    public Integer output5v() {
        Integer output220V = super.output220V()/44;
        System.out.println("转化后变成"+output220V+"V");
        return output220V;
    }
}
