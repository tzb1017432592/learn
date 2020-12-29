package com.example.proxy;

import com.example.proxy.p1.Apple;
import com.example.proxy.p1.IPhone;
import com.example.proxy.p1.ProxyFatory;
import com.example.proxy.p2.OrderProxy;
import com.example.proxy.p2.OrderService;
import com.example.proxy.p2.OrderServiceImpl;
import com.example.proxy.p3.CustomerProxy;
import com.example.proxy.p3.CustomerServiceImpl;
import net.sf.cglib.proxy.Enhancer;

public class Test {
    @org.junit.Test
    public void test1() {
        Apple proxyInstance = (Apple) ProxyFatory.getProxyInstance(new IPhone());
        String iPhone11 = proxyInstance.getIdAndName(1, "IPhone1");
        System.out.println(iPhone11);
    }

    @org.junit.Test
    public void test12() {
        OrderService orderService = (OrderService) new OrderProxy(new OrderServiceImpl()).bind();
        orderService.insertData(1);
    }

    @org.junit.Test
    public void test112() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(CustomerServiceImpl.class);
        enhancer.setCallback(new CustomerProxy());
        CustomerServiceImpl customerService = (CustomerServiceImpl) enhancer.create();
        customerService.insertData(1);
    }
}
