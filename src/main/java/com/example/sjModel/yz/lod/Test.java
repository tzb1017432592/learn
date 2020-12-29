package com.example.sjModel.yz.lod;

public class Test {
    public static void main(String[] args) {
        Me me = new Me();
        IPone11 iPone11 = new IPone11();
        iPone11.setImei(true);
        iPone11.setPingmu(true);
        me.checkIphone(iPone11, "苹果旗舰店", new App("拼多多", new IPhoneStore()));
    }
}
