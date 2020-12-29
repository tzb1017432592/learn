package com.example.sjModel.model23.factory.abstractF;

public class XiaoMiFactory extends ProductFactory {
    @Override
    public Phone createPhone() {
        return new XiaoMiPhone();
    }

    @Override
    public Watch createWatch() {
        return new XiaoMiWatch();
    }
}
