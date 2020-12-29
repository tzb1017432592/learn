package com.example.reflectLearn.clazzLoader;

import org.junit.Test;
import sun.misc.Launcher;

import java.io.*;

public class CustomLoader extends ClassLoader {//自定义加载器需要继承ClassLoader类

    private String name;

    public CustomLoader(String name) {
        this.name = name;
    }

    @Override
    protected Class<?> findClass(String name) {//自定义类需要重写findClass方法
        byte[] xd = xd();
        return defineClass(name, xd, 0, xd.length);//获取了类的字节流，生成class对象
    }

    public byte[] xd() {
        String name = this.name;
        FileInputStream fileInputStream = null;
        ByteArrayOutputStream byteArrayOutputStream = null;
        byte[] bs=new byte[1024];
        try {
            fileInputStream = new FileInputStream(new File(name));//类的路径
            byteArrayOutputStream = new ByteArrayOutputStream();
            int len ;
            while ((len = fileInputStream.read(bs)) != -1) {//读取类的字节流
                byteArrayOutputStream.write(bs,0,len);//获取类的字节流
            }
            return byteArrayOutputStream.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                byteArrayOutputStream.close();
                fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static void main(String[] args) throws ClassNotFoundException {
        CustomLoader customLoader = new CustomLoader("E:\\javawork\\learn\\src\\main\\java\\com\\example\\reflectLearn\\clazzLoader\\Test.class");
        Class<?> aClass = customLoader.loadClass("E:\\javawork\\learn\\src\\main\\java\\com\\example\\reflectLearn\\clazzLoader\\Test.class");
        System.out.println(aClass);
    }
}
