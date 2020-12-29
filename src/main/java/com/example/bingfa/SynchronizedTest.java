package com.example.bingfa;

public class SynchronizedTest{
    public synchronized void methodtest(){
        System.out.println("方法锁");
    }
    public void objectlock(){
        synchronized (this){
            System.out.println("对象锁");
        }
    }
    public void classlock(){
        synchronized (SiSuo.class) {
            System.out.println("类锁");
        }
    }
    public synchronized static void staticlock(){
     System.out.println("静态方法锁");
    }
    public static void main(String[] args) throws InterruptedException {
        Runnable runnable=new Runnable() {
            @Override
            public void run() {
                long start = System.currentTimeMillis();
                    try {
                        while (!Thread.currentThread().isInterrupted()) {
                            Thread.sleep(100);
                            System.out.println("线程：" + Thread.currentThread().getName() + ":" + Thread.currentThread().isInterrupted());
                        }
                    } catch (InterruptedException e) {
                        System.err.println(e);
                    }

                System.out.println("线程："+Thread.currentThread().getName()+"退出阻塞");
                System.out.println("总共费时："+(System.currentTimeMillis()-start)+"毫秒");
            }
        };
        Thread t=new Thread(runnable,"t1");
        t.start();
        Thread.sleep(300);
        t.interrupt();
        Thread.sleep(300);
        System.out.println("线程状态"+t.getName()+":"+t.getState());
        System.out.println("此时我");
        t.start();

    }


}
