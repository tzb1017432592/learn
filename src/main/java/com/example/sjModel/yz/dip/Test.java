package com.example.sjModel.yz.dip;


public class Test {
    public static void main(String[] args) {
        Person me = new Me();
        Person huahua = new HuaHua();
        me.usePhone(new XiaoMi());
        me.usePhone(new IPhone());
        huahua.usePhone(new XiaoMi());
        huahua.usePhone(new IPhone());
    }
}
