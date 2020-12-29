package com.example.sjModel.yz.lod;

public class Me {
    public void checkIphone(IPone11 iPone11, String storeName, App pinduoduo) {
        pinduoduo.setStoreName(storeName);
        if (iPone11.isImei() && iPone11.isPingmu()) {
            pinduoduo.isGive(true);
        } else {
            pinduoduo.isGive(false);
        }
    }
}
