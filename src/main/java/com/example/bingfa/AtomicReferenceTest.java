package com.example.bingfa;

import java.util.concurrent.atomic.AtomicReference;

public class AtomicReferenceTest {
    private static AtomicReference<Integer> compare =
            new AtomicReference<>(0);

    public void test1() throws InterruptedException {
        //比较值后在赋值，赋值成功则返回true,否则false
        System.out.println(compare.compareAndSet(0, 1));//true
        System.out.println(compare.compareAndSet(2, 1));//false
        System.out.println(compare.compareAndSet(1, 3));//true
        System.out.println(compare.get());
    }

    public static void main(String[] args) throws InterruptedException {
        AtomicReferenceTest atomicReferenceTest = new AtomicReferenceTest();
        atomicReferenceTest.test1();
    }
}
