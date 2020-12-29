package com.example.bingfa;

import org.junit.Test;

import java.util.HashMap;

/**
 * 死锁
 * tzb
 */
public class SiSuo {
    private final static Object lock1=new Object();
    private final static Object lock2=new Object();

    public void getLock1(){
        synchronized (lock1){
            System.out.println(Thread.currentThread().getName()+"获取到了【lock1】业务执行中..........");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"等待【lock2】.................");
            getLock2();
        }
    }

    private void getLock2() {
        synchronized (lock2){
            System.out.println(Thread.currentThread().getName()+"获取到了【lock2】业务执行中..........");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"等待【lock1】.................");
            getLock1();
        }
    }

    public static void main(String[] args) {
        final SiSuo siSuo=new SiSuo();
        new Thread(()->{
           while (true){
              siSuo.getLock1();
           }
        }).start();
        new Thread(()->{
            while (true){
                siSuo.getLock2();
            }
        }).start();
    }

    @Test
    public void test11(){
        HashMap<String,String> hashMap=new HashMap<>();
        hashMap.put("ddd","ddd");
    }
}
