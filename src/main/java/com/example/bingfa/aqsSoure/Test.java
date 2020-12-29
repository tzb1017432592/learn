package com.example.bingfa.aqsSoure;

import com.example.bingfa.AtomicIntegerFieldUpdaterTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class Test {
    private static final ExecutorService executorService =
            Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()*2+1);
    private  static final AtomicInteger ATOMIC_INTEGER=new AtomicInteger(0);
    private static final CountDownLatch countDownLatch = new CountDownLatch(10000);
    public static void main(String[] args) throws InterruptedException {
        System.out.println("等待10000个人踢足球.................");
        for (int i=0;i<10000;i++) {
            executorService.submit(() -> {
                ATOMIC_INTEGER.incrementAndGet();
                countDownLatch.countDown();
            });
        }
        executorService.shutdown();
        System.out.println("await前看看人齐了吗："+ATOMIC_INTEGER.get());
        countDownLatch.await();
        System.out.println("await后看看人齐了了吗");
        System.out.println(ATOMIC_INTEGER.get()+"人全到齐了，可以开始踢足球了");
    }
}
