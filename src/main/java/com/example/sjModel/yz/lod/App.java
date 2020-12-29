package com.example.sjModel.yz.lod;

public class App {
    private String name;
    private String storeName;
    private IPhoneStore iPhoneStore;

    public App(String name, IPhoneStore iPhoneStore) {
        this.name = name;
        this.iPhoneStore = iPhoneStore;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public void isGive(boolean ipone) {
        this.iPhoneStore.setPintaiName(name);
        this.iPhoneStore.setName(storeName);
        if (ipone) {
            iPhoneStore.receive();
        } else {
            iPhoneStore.punish();
        }
    }
}
