package com.example.bingfa.volatitletest;

public class Test {
    static boolean flag=false;

    private static void doSomething() {
        System.out.println(">>>>>test----");
    }

    public static void main(String[] args) {
        Runnable runnable=()->{
            flag = false;
            while(!flag){
                doSomething();
            }
        };
        Runnable runnable2=()->{
            flag = true;
        };
        Thread thread=new Thread(runnable);
        Thread thread2=new Thread(runnable2);

        thread.start();
        thread2.start();

    }
}
