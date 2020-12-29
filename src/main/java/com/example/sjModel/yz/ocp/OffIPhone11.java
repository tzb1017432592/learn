package com.example.sjModel.yz.ocp;

public class OffIPhone11 extends IPhone11 {
    public OffIPhone11(String name, int price) {
        super(name, price);
    }

    @Override
    public int getPrice() {
        return super.getPrice() - 500;
    }
}
