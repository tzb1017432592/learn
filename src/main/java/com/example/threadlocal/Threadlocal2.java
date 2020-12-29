package com.example.threadlocal;

import org.junit.Test;

public class Threadlocal2 {
    private static ThreadLocal<String> tl = new ThreadLocal<>();

    public static void getSession(int id) {
        String session = SessionBean.getSession(id);
        tl.set(session);
    }

    @Test
    public void service1() {
        getSession(1);
        tl.remove();
        System.out.println(tl.get());
    }

    enum SessionBean {
        SESSION1(1, "session1"),
        SESSION2(2, "session2"),
        SESSION3(3, "session3"),
        SESSION4(4, "session5");
        private int id;
        private String code;

        SessionBean(int id, String code) {
            this.id = id;
            this.code = code;
        }

        public static String getSession(int id) {
            for (SessionBean sessionBean : SessionBean.values()) {
                if (sessionBean.id == id) {
                    return sessionBean.code;
                }
            }
            return null;
        }
    }

}
