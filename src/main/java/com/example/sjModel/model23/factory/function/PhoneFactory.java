package com.example.sjModel.model23.factory.function;

public abstract class PhoneFactory {
    public abstract Phone createPhone();

    public void say() {
        System.out.println("这是我能自己干的活，已经明确具体的事情");
    }
}
