package com.example.sjModel.model23.zhuangshi;

public class ConcreteDecoratorDoor extends Decorator {
    public ConcreteDecoratorDoor(Component component) {
        super(component);
    }
    private void method1(){
        System.out.println("装个门");
    }
    private void method2(){
        System.out.println("门上装个眼睛");
    }

    @Override
    public void operate() {
        super.operate();
        method1();
        method2();
    }
}
