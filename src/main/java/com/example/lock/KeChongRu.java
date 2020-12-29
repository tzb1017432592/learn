package com.example.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class KeChongRu {
    private static ReentrantLock lock = new ReentrantLock(true);

    private static int count = 0;

    public static void test() {
        new ReentrantReadWriteLock().readLock();
        lock.lock();
        try {
            //记录线程在释放锁之间重复获取过几次锁
            //如果没有重复获取10次锁，就继续递归下去，直至重复获取10次锁
            if ((count = lock.getHoldCount()) < 10) {
                test();
            }
        } finally {
            lock.lock();
        }
    }

    public static void main(String[] args) {
        test();
        System.out.println(count);
    }
}
