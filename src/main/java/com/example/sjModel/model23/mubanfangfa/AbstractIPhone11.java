package com.example.sjModel.model23.mubanfangfa;

public abstract class AbstractIPhone11 {
    public void battery(){
        System.out.println("电池：3110mAh");
    }
    public void CPU(){
        System.out.println("CPU:A13");
    }
    public void RAM(){
        System.out.println("RAM:4GB");
    }
    public void bulid(String c){
        System.out.println("顾客"+c+"你的手机配置为：");
        battery();
        CPU();
        RAM();
        coustomer();
        System.out.println("");
    }
    //提供顾客填写需要的配置
    public abstract void coustomer();

}
