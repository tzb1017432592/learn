package com.example.proxy.p1;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyProxy implements InvocationHandler {
    private Object obj;

    public void bind(Object obj) {
        this.obj = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        proxy = obj;
        System.out.println("增强方法是:"+method);
        System.out.println("前置增强");
        Object invoke = method.invoke(proxy, args);
        System.out.println("后置增强");
        return invoke;
    }
}
