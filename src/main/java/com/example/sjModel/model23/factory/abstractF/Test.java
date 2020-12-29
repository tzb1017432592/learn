package com.example.sjModel.model23.factory.abstractF;

import com.example.sjModel.yz.dip.XiaoMi;

public class Test {
    @org.junit.Test
    public void test1() {
        //专门生产苹果产品的工厂
        ProductFactory apple = new AppleFactory();
        Phone iphone = apple.createPhone();
        Watch iwatch = apple.createWatch();
        iphone.telephone();
        iwatch.LookTime();
        //专门生产小米产品的工厂
        ProductFactory xiaomi = new XiaoMiFactory();
        Phone xiaomiPhone = xiaomi.createPhone();
        Watch xiaomiWatch = xiaomi.createWatch();
        xiaomiPhone.telephone();
        xiaomiWatch.LookTime();

    }

}
