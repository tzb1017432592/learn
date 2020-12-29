package com.example.threadlocal;

public enum SessionBean {
    SESSION1(1, "session1"), SESSION2(2, "session2"), SESSION3(3, "session3"), SESSION4(4, "session5");
    private int id;
    private String code;

    SessionBean(int id, String code) {
        this.id = id;
        this.code = code;
    }

    public int getId() {
        return id;
    }

    public String getCode() {
        return code;
    }
}
