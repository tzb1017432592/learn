package com.example.sjModel.yz.ocp;

public class Pinduoduo {
    public static void main(String[] args) {
        Goods iPhone11 = new OffIPhone11("IPhone11", 5499);
        System.out.println(iPhone11.getName() + "在拼多多卖" + iPhone11.getPrice());
    }
}
