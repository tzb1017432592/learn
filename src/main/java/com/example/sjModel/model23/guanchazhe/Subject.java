package com.example.sjModel.model23.guanchazhe;

import java.util.ArrayList;
import java.util.List;

public abstract class Subject {
    private List<Observer> observers=new ArrayList<>();

    public void addObserver(Observer observer){
        observers.add(observer);
    }

    public void delObserver(Observer observer){
        observers.remove(observer);
    }

    public void notifyObservers(String action){
        observers.forEach(o->o.update(action));
    }

    abstract void playBasketball();

    abstract void rap();

    abstract void dance();
}
