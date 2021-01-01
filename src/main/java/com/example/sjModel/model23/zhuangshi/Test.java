package com.example.sjModel.model23.zhuangshi;

public class Test {
    public static void main(String[] args) {
        Component mopifang=new ConcreteComponent();
        mopifang=new ConcreteDecoratorDoor(mopifang);
        mopifang=new ConcreteDecoratorWindow(mopifang);
        mopifang.operate();
    }
}
