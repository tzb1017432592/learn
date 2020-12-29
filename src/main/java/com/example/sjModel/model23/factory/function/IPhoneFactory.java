package com.example.sjModel.model23.factory.function;

public class IPhoneFactory extends PhoneFactory {
    @Override
    public Phone createPhone() {
        return new IPhone();
    }
}
