package com.example.sjModel.model23.guanchazhe;

public class ConcreteObserverDog2 implements Observer{
    private String name;

    public ConcreteObserverDog2(String name) {
        this.name = name;
    }

    @Override
    public void update(String action) {
        System.out.println(name+"爆料"+action);
    }
}
