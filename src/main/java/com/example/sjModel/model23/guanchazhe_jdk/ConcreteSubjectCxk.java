package com.example.sjModel.model23.guanchazhe_jdk;

import java.util.Observable;
public class ConcreteSubjectCxk extends Observable implements Icxk{
    private final static String NAME="蔡徐坤";

    @Override
    public void playBasketball() {
        super.setChanged();
        String action=NAME+"打篮球";
        System.out.println(NAME+"打篮球");
        super.notifyObservers(action);
    }

    @Override
    public void rap() {
        super.setChanged();
        String action=NAME+"唱rap";
        System.out.println(action);
        super.notifyObservers(action);
    }

    @Override
    public void dance() {
        super.setChanged();
        String action=NAME+"跳舞";
        System.out.println(NAME+"跳舞");
        super.notifyObservers(action);
    }
}
