package com.example.sjModel.model23.zhuangshi;

public abstract class Decorator implements Component{
    private Component component=null;
    //用于传递被装饰者
    public Decorator(Component component) {
        this.component = component;
    }

    //给具体装饰者执行，并在其之上进行添加前后置功能
    @Override
    public void operate() {
        this.component.operate();
    }
}
