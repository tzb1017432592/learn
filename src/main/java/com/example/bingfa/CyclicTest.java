package com.example.bingfa;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class CyclicTest {
    private static ExecutorService executorService = Executors.newCachedThreadPool();
    private static AtomicInteger count= new AtomicInteger();
   private static CyclicBarrier cyclicBarrier = new CyclicBarrier(4, new Runnable() {
        @Override
        public void run() {
            if (count.get()==0) {
                System.out.println("4个人到齐了，可以打麻将了");
            }else {
                System.out.println("大家还想打一局");
            }
        }
    });

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 8; i++) {
            Thread.sleep(100);
            executorService.submit(() -> {
                try {
                    if (count.get()==0) {
                        System.out.println(String.format("%s:%s", Thread.currentThread().getName(), "来打麻将了"));
                        cyclicBarrier.await();
                        System.out.println(String.format("%s:%s", Thread.currentThread().getName(), "开始摸牌"));
                        count.incrementAndGet();
                    }else {
                        System.out.println(String.format("%s:%s", Thread.currentThread().getName(), "想在玩一局"));
                        cyclicBarrier.await();
                        System.out.println(String.format("%s:%s", Thread.currentThread().getName(), "第二局开始摸牌"));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
        executorService.shutdown();
        while (!executorService.isTerminated()) {

        }
        System.out.println("这局结束后都累了,大家回去睡觉了");
    }

}
