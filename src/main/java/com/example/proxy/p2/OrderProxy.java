package com.example.proxy.p2;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class OrderProxy implements InvocationHandler {

    private Object target;

    public OrderProxy(Object target) {
        this.target = target;
    }

    //前置增强
    private void before(Object o){
        if (o instanceof Integer) {
            System.out.println("OrderService的前置增强....");
        }

    }
    //后置增强
    private void after(Object o){
        if (o instanceof Integer) {
            System.out.println("OrderService后置增强......");
        }
    }
    public Object bind(){
        Class aClass = target.getClass();
        return Proxy.newProxyInstance(aClass.getClassLoader(),aClass.getInterfaces(),this);
    }
    //proxy代理类（这个很少用的），method被代理对象需要增强的方法，被代理对象执行方法的参数
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //增强方法的参数，0代表第一个参数
        Object arg = args[0];
        before(arg);
        //需要增强的方法
        Object invoke = method.invoke(target, arg);
        after(arg);
        return invoke;
    }
}
