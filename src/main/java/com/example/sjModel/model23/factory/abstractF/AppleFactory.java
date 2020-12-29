package com.example.sjModel.model23.factory.abstractF;

public class AppleFactory extends ProductFactory {
    @Override
    public Phone createPhone() {
        return new IPhone();
    }

    @Override
    public Watch createWatch() {
        return new Iwatch();
    }
}
