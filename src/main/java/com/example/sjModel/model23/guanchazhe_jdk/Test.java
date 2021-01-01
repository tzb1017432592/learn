package com.example.sjModel.model23.guanchazhe_jdk;

import java.util.Observable;

public class Test {
    public static void main(String[] args) {
        java.util.Observer observer1=new ConcreteObserverDog1("狗子队1号");
        java.util.Observer observer2=new ConcreteObserverDog2("狗子队2号");
        java.util.Observer observer3=new ConcreteObserverDog3("狗子队3号");
        ConcreteSubjectCxk cxk=new ConcreteSubjectCxk();
        //jdk的观察者模式是从集合的末尾执行
        cxk.addObserver(observer3);
        cxk.addObserver(observer2);
        cxk.addObserver(observer1);
        cxk.playBasketball();

    }
}
