package com.example.proxy.p3;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CustomerProxy implements MethodInterceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("Customer的前置增强....");
        Object o1 = methodProxy.invokeSuper(o, objects);
        System.out.println("Customer后置增强......");
        return o1;
    }
}
