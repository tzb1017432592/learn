package com.example.sjModel.model23.factory.function;

public class Test {
    @org.junit.Test
    public void test1() {
        PhoneFactory iphoneFactory = new IPhoneFactory();
        Phone iphone = iphoneFactory.createPhone();
        iphone.net();
        PhoneFactory xiaomiFactory = new XiaoMiFactory();
        Phone xiaoMi = xiaomiFactory.createPhone();
        xiaoMi.net();
    }
}
