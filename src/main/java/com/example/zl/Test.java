package com.example.zl;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public abstract class Test {
    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(10);
        for(int i=0;i<10;i++){
            new Thread(()->{
                try {
                    Lock lock=new ExcutorZkLock();
                    lock.getLock();
                    TimeUnit.SECONDS.sleep(5);
                    lock.unLock();
                    countDownLatch.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            },"thread--"+i).start();
            countDownLatch.countDown();
            System.out.println("线程结束");
        }
    }
    @org.junit.Test
    public void test(){
        System.out.println(System.currentTimeMillis()+"11111");
    }

    public void stst(String s){
        s="dddd";
    }
    @org.junit.Test
    public void test2(){
        String s="ccc";
        stst(s);
        System.out.println(s);
    }
}
