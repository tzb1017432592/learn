package com.example.proxy.p2;

public class OrderServiceImpl implements OrderService {
    @Override
    public int insertData(Integer i) {
        System.out.println("执行插入业务");
        return i;
    }
}
