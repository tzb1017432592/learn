package com.example.bingfa.queue;

import java.util.concurrent.LinkedBlockingQueue;

public class BlockingQueueTest {
    private int i;
    LinkedBlockingQueue<Integer> queue = new LinkedBlockingQueue<>(3);

    class Producer implements Runnable {
        @Override
        public void run() {
            while (true) {
                try {
                    System.out.println("-----------------------------------------------------");
                    Thread.sleep(100);
                    i++;
                    queue.put(i);
                    System.out.println("生产的数据："+i);
                    System.out.println("队列内的数据：" + queue);
                    System.out.println("生产后剩余容量：" + queue.remainingCapacity());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class Consumer implements Runnable {
        @Override
        public void run() {
            while (true) {
                try {
                    System.out.println("-----------------------------------------------------");
                    Thread.sleep(200);
                    Integer take = queue.take();
                    System.out.println("消费的数据：" + take);
                    System.out.println("消费后队列内的数据：" + queue);
                    System.out.println("消费后队列剩余容量：" + queue.remainingCapacity());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        BlockingQueueTest linkedBlockingQueueTest = new BlockingQueueTest();
        new Thread(linkedBlockingQueueTest.new Producer()).start();
        new Thread(linkedBlockingQueueTest.new Consumer()).start();
    }
}
