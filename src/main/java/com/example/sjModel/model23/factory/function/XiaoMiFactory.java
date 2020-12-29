package com.example.sjModel.model23.factory.function;

public class XiaoMiFactory extends PhoneFactory {
    @Override
    public Phone createPhone() {
        return new XiaoMi();
    }
}
