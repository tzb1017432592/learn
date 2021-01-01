package com.example.sjModel.model23.zhuangshi;

public class ConcreteDecoratorWindow extends Decorator {
    public ConcreteDecoratorWindow(Component component) {
        super(component);
    }

    private void method1(){
        System.out.println("装个窗户");
    }
    private void method2(){
        System.out.println("窗户换上防弹玻璃");
    }

    @Override
    public void operate() {
        super.operate();
        method1();
        method2();
    }
}
