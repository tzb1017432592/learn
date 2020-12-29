package com.example.bingfa;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

public  class LongAddTest {
    private static ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()*2+1);
    private static LongAdder longAdder=new LongAdder();
    CountDownLatch countDownLatch = new CountDownLatch(1000);
    public void test1() throws InterruptedException {
        for (int i = 0; i < 1000; i++) {
            executorService.submit(() -> {
                longAdder.increment();
                //加一
                countDownLatch.countDown();
            });
        }
        executorService.shutdown();
        countDownLatch.await();
        System.out.println(longAdder.longValue());
    }

    public static void main(String[] args) throws InterruptedException {
        LongAddTest longAddTest=new LongAddTest();
        longAddTest.test1();
    }
}
