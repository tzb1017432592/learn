package com.example.bingfa.singleton;

public class Singleton {
    private Singleton(){

    }
    private static volatile Singleton unsafeObject=null;
    //线程不安全，发生了指令重排，原因是unsafeObject是不可见的
    public static Singleton unsafeObject(){
        if (unsafeObject==null){
            synchronized (Singleton.class){
                if (unsafeObject==null) {
                    return new Singleton();
                }
            }
        }
        return unsafeObject;
    }
}
