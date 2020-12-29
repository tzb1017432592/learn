package com.example.sjModel.model23.factory.abstractF;

public abstract class ProductFactory {
    public abstract Phone createPhone();

    public abstract Watch createWatch();

    public void say() {
        System.out.println("这是我能自己干的活，已经明确具体的事情," +
                "我做的事情,造电子产品的任务交给子类就好了");
    }
}
