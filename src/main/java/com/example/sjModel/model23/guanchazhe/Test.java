package com.example.sjModel.model23.guanchazhe;

public class Test {
    public static void main(String[] args) {
        Observer observer1=new ConcreteObserverDog1("狗子队1号");
        Observer observer2=new ConcreteObserverDog2("狗子队2号");
        Observer observer3=new ConcreteObserverDog3("狗子队3号");
        Subject cxk=new ConcreteSubjectCxk();
        cxk.addObserver(observer1);
        cxk.addObserver(observer2);
        cxk.addObserver(observer3);

        cxk.playBasketball();


    }


}
