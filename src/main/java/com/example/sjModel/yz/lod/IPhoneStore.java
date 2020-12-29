package com.example.sjModel.yz.lod;

public class IPhoneStore {
    private String name;
    private String pintaiName;

    public void setPintaiName(String pintaiName) {
        this.pintaiName = pintaiName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void receive() {
        System.out.println(name + "货真价实," + pintaiName + "平台已经打款钱了");
    }

    public void punish() {
        System.out.println(name + "卖假货," + pintaiName + "平台将其进行处罚了");
    }
}
