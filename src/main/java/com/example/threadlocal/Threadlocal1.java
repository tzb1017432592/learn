package com.example.threadlocal;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Threadlocal1 {
    private static ExecutorService fl = Executors.newFixedThreadPool(5);
    private static ThreadLocal<SimpleDateFormat> tl = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        }
    };

    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            int a = i;
            fl.submit(
                    () -> {
                        SimpleDateFormat simpleDateFormat = tl.get();
                        System.out.println(simpleDateFormat.format(new Date(10000 * a)));
                    }
            );
        }
        fl.shutdown();
    }
}
