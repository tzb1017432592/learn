package com.example.proxy.p1;

import java.lang.reflect.Proxy;

public class ProxyFatory {
    //obj是被代理的对象
    public static Object getProxyInstance(Object obj) {
        //对人
        MyProxy myProxy = new MyProxy();
        myProxy.bind(obj);
        //返回的是代理人myProxy的invoke方法所返回的对象
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(),
                obj.getClass().getInterfaces(), myProxy);
    }
}
