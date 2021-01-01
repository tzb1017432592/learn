package com.example.sjModel.model23.guanchazhe;

public class ConcreteSubjectCxk extends Subject {
    private final static String NAME="蔡徐坤";
    @Override
    void playBasketball() {
        String action=NAME+"打篮球";
        System.out.println(NAME+"打篮球");
        super.notifyObservers(action);
    }

    @Override
    void rap() {
        String action=NAME+"唱rap";
        System.out.println(action);
        super.notifyObservers(action);
    }

    @Override
    void dance() {
        String action=NAME+"跳舞";
        System.out.println(NAME+"跳舞");
        super.notifyObservers(action);
    }
}
