package com.example.sjModel.yz.ocp;

public class IPhone11 implements Goods {
    private String name;
    private int price;

    public IPhone11() {
    }

    public IPhone11(String name, int price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public int getPrice() {
        return this.price;
    }

    @Override
    public String getName() {
        return this.name;
    }


}
