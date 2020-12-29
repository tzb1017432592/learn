package com.example.sjModel.yz.dip;

public class Me implements Person {
    @Override
    public void usePhone(Phone phone) {
        System.out.println("我要用" + phone.name() + "打游戏");
    }

}
