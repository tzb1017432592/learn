package com.example.bingfa;

import org.junit.Test;

import java.lang.ref.SoftReference;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestCountDownLatch {

    private final static int SN = 99;

    private volatile int vcount = 0;
    final Object olock = new Object();
    private final static int CN = 5;
    //设置计数器为5
    CyclicBarrier cyclicBarrier = new CyclicBarrier(CN);
    private final static Lock lock = new ReentrantLock();
    private final static int LN = 5000;
    private final static int BN = 10;
    final ScheduledExecutorService sch = Executors.newScheduledThreadPool(1);
    final BlockingQueue<Integer> blockingQueue = new LinkedBlockingQueue(BN);
    private static ExecutorService executorService = Executors.newCachedThreadPool();
    //设置99个许可证
    Semaphore semaphore = new Semaphore(1);
    private final static int TN = 1000;
    //计数器设置为线程数
    CountDownLatch countDownLatch = new CountDownLatch(TN);
    private int count = 0;
    //原子性的类，原子类使用的CAS乐观序锁机制
    private AtomicInteger atomicInteger = new AtomicInteger(0);

    @Test
    public void test3() throws InterruptedException {
        for (int i = 0; i < TN; i++) {
            executorService.submit(() -> {
                //加一
                atomicInteger.incrementAndGet();
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        System.out.println(atomicInteger.get());
    }


    @Test
    public void test11() throws Exception {
        for (int i = 0; i < LN; i++) {
            blockingQueue.add(count);
            System.out.println(i);
        }
        /*sch.scheduleAtFixedRate(()->{
            blockingQueue.add(count++);
            System.out.println(count);
        },0,1000,TimeUnit.MILLISECONDS);
        for (Integer integer : blockingQueue) {
            System.out.println(integer);
        }*/

    }

    class myCall implements Callable<String> {

        @Override
        public String call() throws Exception {
            System.out.println("call方法执行了");
            Thread.sleep(500);
            return "call";
        }
    }

    @Test
    public void test9() throws Exception {
        FutureTask<String> futureTask = new FutureTask(new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("call方法执行了");
                Thread.sleep(500);
                return "call";
            }
        });
        //只需执行Thread的start
        new Thread(futureTask).start();
        String o = futureTask.get();
        System.out.println("执行get");
        System.out.println(o);
    }

    @Test
    public void test10() throws Exception {
        Future<String> future = executorService.submit(new myCall());
        String o = future.get();
        System.out.println("执行get");
        System.out.println(o);
    }

    @Test
    public void test8() throws Exception {
        for (int i = 0; i < LN; i++) {
            executorService.submit(() -> {
                try {
                    semaphore.acquire();
                    //上锁
                    lock.lock();
                    count++;
                    semaphore.release();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    //解锁
                    lock.unlock();
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        if (!executorService.isTerminated()) {
            executorService.shutdown();
        }
        System.out.println(count);
    }

    @Test
    public void test7() throws InterruptedException {
        for (int i = 0; i < CN; i++) {
            Thread.sleep(100);
            executorService.submit(() -> {
                try {
                    System.out.println(String.format("%s:%s", Thread.currentThread().getName(), "准备就绪"));
                    //计数器减1，在计数器为0前，线程会被阻塞在此
                    cyclicBarrier.await();
                    System.out.println(String.format("%s:%s", Thread.currentThread().getName(), "开始往下走"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
        if (!executorService.isTerminated()) {
            executorService.shutdown();
        }
    }

    @Test
    public void test6() {
        executorService.submit(() -> {
            for (int i = 0; i < 5; i++) {
                //暗示CPU,愿意让出CPU时间
                Thread.yield();
                System.out.println("线程A");

            }
        });
        executorService.submit(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("线程B");
            }
        });
    }

    @Test
    public synchronized void test5() {
        executorService.submit(() -> {
            try {
                synchronized (olock) {
                    //线程A调用了wait()线程被扔进等待池
                    olock.wait();
                    System.out.println("线程A开始");
                    System.out.println("线程A结束");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        executorService.submit(() -> {
            try {
                synchronized (olock) {
                    System.out.println("线程B开始");
                    //等待池中的线程被唤醒，
                    // 线程A被扔进锁池去竞争锁
                    olock.notify();

                    System.out.println("线程B结束");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        });
        if (!executorService.isTerminated()) {
            executorService.shutdown();
        }
    }

    @Test
    public void test4() throws InterruptedException {
        for (int i = 0; i < TN; i++) {
            executorService.submit(() -> {
                //加一
                vcount++;
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        System.out.println(vcount);
    }


    @Test
    public void test2() {
        for (int i = 0; i < 10000; i++) {
            executorService.submit(() -> {
                try {
                    //获取许可证，每执行一次减掉一个许可证，许可证用完后会线程被阻塞
                    semaphore.acquire();
                    count++;
                    // semaphore.release();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
        // System.out.println(count);
        if (!executorService.isTerminated()) {
            executorService.shutdown();
        }
        System.out.println(count);
    }

    @Test
    public void test1() throws InterruptedException {
        for (int i = 0; i < TN; i++) {
            executorService.submit(() -> {
                count++;
                //计数器减1
                countDownLatch.countDown();
            });
        }
        System.out.println("主线程等待其他线程执行完毕");
        //在计数器为不为0时阻塞主线程，为0时主线程可以往下执行
        countDownLatch.await();
        executorService.shutdown();
        System.out.println(String.format("%s:%s", "最终结果", count));
    }
}
