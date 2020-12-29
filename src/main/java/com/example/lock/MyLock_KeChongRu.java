package com.example.lock;

public class MyLock_KeChongRu {
    private boolean isFirstGetLock = false;
    private Thread hasLockThread = null;
    private int hasLockCount = 0;
    final Object olock = new Object();

    public void lock() throws Exception {
        synchronized (olock) {
            Thread t = Thread.currentThread();
            isHasProsse(t);
            hasLockThread = t;
            hasLockCount++;
            isFirstGetLock = true;
        }
    }

    //判断线程是否是有锁的
    private void isHasProsse(Thread t) {
        try {
            //isFirstGetLock为false表示没有任何线程拿过锁
            // isFirstGetLock为true表示线程拿过了锁，并判断是不是当前线程持有的
            //如果不是当前线程持有，则让线程让出锁和cpu时间
            if (isFirstGetLock && hasLockThread != t) {
                //将线程的锁及CPU时间让出来
                olock.wait();
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void unlock() {
        synchronized (olock) {
            Thread t = Thread.currentThread();
            if (hasLockCount < 0) {
                return;
            }
            if (isFirstGetLock && hasLockThread == t) {
                hasLockCount--;
            }
            //当前线程已经把锁归还完，唤醒其他线程让他去争夺锁
            if (hasLockCount == 0) {
                hasLockThread = null;
                isFirstGetLock = false;
                olock.notify();
            }
        }
    }

    public int getHasLockCount() {
        return hasLockCount;
    }
}
