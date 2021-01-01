package com.example.sjModel.model23.guanchazhe_jdk;

import java.util.Observable;
import java.util.Observer;

public class ConcreteObserverDog1 implements Observer {
    private String name;
    public ConcreteObserverDog1(String name) {
        this.name = name;
    }
    @Override
    public void update(Observable o, Object arg) {
        System.out.println(name+"爆料"+arg.toString());
    }
}
