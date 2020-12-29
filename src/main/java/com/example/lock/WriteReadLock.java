package com.example.lock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class WriteReadLock {
    private final static ReentrantReadWriteLock rrw = new ReentrantReadWriteLock();
    private final static ReentrantReadWriteLock.ReadLock readLock = rrw.readLock();
    private final static ReentrantReadWriteLock.WriteLock writeLock = rrw.writeLock();

    private static void ReadUp() {
        readLock.lock();
        String name = Thread.currentThread().getName();
        try {
            System.out.println(name + ":读锁等待升级为写锁");
            writeLock.lock();
            System.out.println(name + ":读锁升级为写锁");
        } finally {
            writeLock.unlock();
            System.out.println(name + ":演示结束");
            readLock.unlock();
        }
    }

    private static void WriteFall() {
        writeLock.lock();
        String name = Thread.currentThread().getName();
        try {
            System.out.println(name + ":写锁等待降级为读书");
            readLock.lock();
            System.out.println(name + ":写锁降级为读锁");
        } finally {
            readLock.unlock();
            System.out.println(name + ":演示结束");
            writeLock.unlock();
        }
    }

    public static void main(String[] args) {
        Thread thread = new Thread(() -> ReadUp());
        Thread thread1 = new Thread(() -> ReadUp());
        Thread thread2 = new Thread(() -> ReadUp());
        thread.start();
        thread1.start();
        thread2.start();
    }
}
