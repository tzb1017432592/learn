package com.example.bingfa.singleton;
//线程安全
public class SingletonEunm {
    private SingletonEunm(){}
    //枚举类相比饿汉模式更加节省资源，原因是调用的时候才创建
    private static SingletonEunm getSingletonEunm(){
        return InstanceSingleton.INSTANCE.getSingleton();
    }
    private enum InstanceSingleton{
        INSTANCE;
        private SingletonEunm singletonEunm;
        //对于枚举类JVM保证只初始化一次，并且是调用的时候才初始化
        InstanceSingleton(){
            singletonEunm=new SingletonEunm();
        }
        public SingletonEunm getSingleton(){
            return singletonEunm;
        }
    }
}
