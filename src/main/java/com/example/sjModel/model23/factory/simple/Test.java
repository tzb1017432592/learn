package com.example.sjModel.model23.factory.simple;

public class Test {
    public static void main(String[] args) {
        IPhone iphone = PhoneFactory.createPhone(IPhone.class);
        HuaWei huaWei = PhoneFactory.createPhone(HuaWei.class);
        if ((iphone != null && iphone instanceof IPhone)) {
            System.out.println("iphone创建成功");
        } else {
            System.out.println("iphone创建失败");
        }
        if ((huaWei != null && huaWei instanceof HuaWei)) {
            System.out.println("HuaWei创建成功");
        } else {
            System.out.println("HuaWei创建失败");
        }
    }
}
