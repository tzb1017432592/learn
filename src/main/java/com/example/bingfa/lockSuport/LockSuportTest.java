package com.example.bingfa.lockSuport;

import java.util.concurrent.locks.LockSupport;

public class LockSuportTest {
    public static void main(String[] args) {
        LockSuportTest lockSuportTest=new LockSuportTest();

        LockSupport.park(lockSuportTest);

        System.out.println("1111111");

    }
}
