package com.example.reflectLearn.test;


import java.util.ArrayList;

public class FinalizeTest {
    public static FinalizeTest alive = null;

    public void isLive() {
        System.out.println("我还活着");
    }

    /**
     * 覆盖了finalize方法
     *
     * @throws Throwable
     */
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("finalize方法调用了！！！！");
        alive = this;
    }

    public static void main(String[] args)
            throws Exception {
        alive = new FinalizeTest();
        alive = null;
        System.gc();
        Thread.sleep(500);
        //第一次gc回收,对象执行了finalize逃过一劫
        if (alive != null) {
            alive.isLive();
        } else {
            System.out.println("我被回收了");
        }
        System.out.println("-------------------------");
        alive = null;
        System.gc();
        Thread.sleep(500);
        //第二次回收，这次gc判断到对象已经执行过了finalize，
        // 不会再执行直接回收掉了
        if (alive != null) {
            alive.isLive();
        } else {
            System.out.println("我被回收了");
        }
    }
}
