package com.example.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Beiguang {
    private static Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>();
     /*   ConcurrentHashMap;
        CopyOnWriteArrayList;
        BlockingQueue;
        ArrayBlockingQueue;*/
        map.put(null, "sdsd");
        map.put(null, null);
        /*for (Map.Entry<String, String> e : map.entrySet()) {
            System.out.println(e.getKey());
        }*/
        System.out.println(map.get(null));
      /*  lock.lock();
        try {
            System.out.println("这个一个悲观锁的简单演示");
        }finally {
            lock.unlock();
        }*/
    }
}
