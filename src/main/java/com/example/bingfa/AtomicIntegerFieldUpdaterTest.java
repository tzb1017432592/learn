package com.example.bingfa;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

public class AtomicIntegerFieldUpdaterTest {
    private static ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()*2+1);
    public volatile int count=0;
    private static AtomicIntegerFieldUpdater<AtomicIntegerFieldUpdaterTest> updater=
            AtomicIntegerFieldUpdater.newUpdater(AtomicIntegerFieldUpdaterTest.class,"count");
    CountDownLatch countDownLatch = new CountDownLatch(1000);


    static AtomicIntegerFieldUpdaterTest bingFa2=new AtomicIntegerFieldUpdaterTest();
    public void test1() throws InterruptedException {
        for (int i = 0; i < 1000; i++) {
            executorService.submit(() -> {
                int qw;
                do {
                     qw = updater.get(bingFa2);
                }while (!updater.compareAndSet(bingFa2,qw,count+1));
                //加一
              countDownLatch.countDown();
            });
        }
        executorService.shutdown();
        countDownLatch.await();
        System.out.println(updater.get(bingFa2));
    }

    public static void main(String[] args) throws InterruptedException {
        bingFa2.test1();
    }
}
