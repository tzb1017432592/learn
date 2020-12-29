package com.example.bingfa.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionTest implements Runnable {
    private final static Lock lock = new ReentrantLock();
    private final static Condition CONDITION = lock.newCondition();

    public void test1() {
        lock.lock();
        try {
            if (Thread.currentThread().getName().equals("t1")) {
                Thread.sleep(100);
                System.out.println(Thread.currentThread().getName() + "拿到锁了");
                System.out.println(Thread.currentThread().getName() + "释放锁，等待通知");
                CONDITION.await();
                System.out.println(Thread.currentThread().getName() + "重新获得锁");
                System.out.println(Thread.currentThread().getName() + "执行结束");
            } else if (Thread.currentThread().getName().equals("t2")){
                Thread.sleep(100);
                System.out.println(Thread.currentThread().getName() + "拿到锁了");
                System.out.println(Thread.currentThread().getName() + "发送通知");
                CONDITION.signal();
                System.out.println(Thread.currentThread().getName() + "执行结束,释放锁");
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            lock.unlock();
        }

    }

    public static void main(String[] args) throws InterruptedException {
        ConditionTest test = new ConditionTest();
        Thread t1 = new Thread(test, "t1");
        Thread t2 = new Thread(test, "t2");
        t1.start();
        t1.interrupt();
        System.out.println(t1.isInterrupted());
        Thread.sleep(300);
        t2.start();

    }

    @Override
    public void run() {
        test1();
    }
}
