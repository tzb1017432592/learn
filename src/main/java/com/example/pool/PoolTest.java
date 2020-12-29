package com.example.pool;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class PoolTest {
    @Test
    public void test1() {
        //核心线程数设置为3
        ScheduledExecutorService scp = Executors.newScheduledThreadPool(3);
        //延迟5秒
        // scp.schedule(new TestTask(),5, TimeUnit.SECONDS);

        //延迟5秒，没两秒执行一次
        scp.scheduleAtFixedRate(new TestTask(), 5, 2, TimeUnit.SECONDS);
    }

    private static ExecutorService fx = Executors.newFixedThreadPool(3);

    class PooTaskReturn implements Callable<String> {
        @Override
        public String call() throws Exception {
            return "有返回值的线程池方法";
        }
    }

    class PoolTask implements Runnable {
        @Override
        public void run() {
            System.out.println("无返回值的线程池方法");
        }
    }

    @Test
    public void test() {
        for (int i = 0; i < 1000; i++) {
            fx.execute(new PoolTask());
            fx.execute(() -> {
                System.out.println("使用匿名内部类");
            });
        }
        List<Future<String>> fls = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            Future<String> submit = fx.submit(new PooTaskReturn());
            Future<String> lambda = fx.submit(() -> {
                return "使用匿名内部类";
            });
            fls.add(submit);
            fls.add(lambda);
        }

        fls.forEach(l -> {
            try {
                System.out.println(l.get());
            } catch (Exception e) {
                System.out.println(e);
            }
        });
        //关闭线程池，使用shutdown关闭时会调用拒绝策略拒绝所有新的任务进入队列，
        // 然后等待所有的任务，包括队列中的任务执行完成
        fx.shutdown();
        //关闭线程池，使用shutdown关闭时会调用拒绝策略拒绝所有新的任务进入队列，
        // 然后把尝试关闭正在执行的任务
        //将队列中的任务直接丢弃
        fx.shutdownNow();
        //所有的任务已经完成，返回true
        fx.isTerminated();
        //收到关闭指令后返回true,即执行了shutdownNow或者shutdown
        fx.isShutdown();
    }

    public static void main(String[] args) {


    /*    ThreadPoolExecutor threadPoolExecutor =
                new ThreadPoolExecutor(3,
                        5,
                        30, TimeUnit.SECONDS,
                        new ArrayBlockingQueue<Runnable>(20));
        //核心线程数设置为3
        ScheduledExecutorService scp = Executors.newScheduledThreadPool(3);
        //延迟5秒
         scp.schedule(new TestTask(),5, TimeUnit.SECONDS);
        //延迟5秒，每两秒执行一次
        scp.scheduleAtFixedRate(new TestTask(),5,2,TimeUnit.SECONDS);*/
    }
}
