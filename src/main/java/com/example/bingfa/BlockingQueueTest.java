package com.example.bingfa;

import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class BlockingQueueTest {
    public static void main(String[] args) throws InterruptedException {
        List<String> list = new ArrayList<>();
        Map<String, String> map = new HashMap<>();
        List<String> list2 = Collections.synchronizedList(list);
        Map<String, String> map2 = Collections.synchronizedMap(map);

        // 声明一个容量为10的缓存队列
        BlockingQueue<String> queue = new LinkedBlockingQueue<String>(10);
        Producer producer1 = new Producer(queue);
        Producer producer2 = new Producer(queue);
        Producer producer3 = new Producer(queue);
        Consumer consumer = new Consumer(queue);
        ExecutorService service = Executors.newCachedThreadPool();
        // 启动线程
        service.execute(producer1);
        service.execute(producer2);
        service.execute(producer3);
        service.execute(consumer);
        // 执行5s
        Thread.sleep(5 * 1000);
        producer1.stop();
        producer2.stop();
        producer3.stop();
        while (!service.isTerminated()) {
            service.shutdown();
        }
    }
}
