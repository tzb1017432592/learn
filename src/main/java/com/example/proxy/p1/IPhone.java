package com.example.proxy.p1;

public class IPhone implements Apple {
    private int id;
    private String name;

    public IPhone(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public IPhone() {
    }

    @Override
    public void printName(String name) {
        System.out.println(name);
    }

    @Override
    public String getIdAndName(int id, String name) {
        return name + id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
