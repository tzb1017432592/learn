package com.example.reflectLearn.test;

import com.example.reflectLearn.bean.Dog;

import java.lang.ref.PhantomReference;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.*;

public class Test {
    static {
        int h = 0;
        int k = 1;
    }

    @org.junit.Test
    public void test1() throws Exception {
        Class<?> clazz = null;
        clazz = Dog.class;//Class.forName("xxxx.xxx.xx")
        Constructor<?>[] constructors = clazz.getDeclaredConstructors();
        Method[] declaredMethods = clazz.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            System.out.println(declaredMethod.getName());
        }
        System.out.println("------------------------------------");
        for (Constructor<?> constructor : constructors) {
            String name = constructor.getName();
            System.out.println(name);
        }
        System.out.println("----------------------");
        Constructor<?> c = clazz.getDeclaredConstructor(String.class, String.class);
        Dog dog = (Dog) c.newInstance("好狗", "公");
        System.out.println(dog);
        //Dog dog = (Dog)clazz.newInstance();
        System.out.println("-------------------------------");
        ClassLoader classLoader = clazz.getClassLoader();
        System.out.println(classLoader.toString());
        System.out.println(String.class.getClassLoader());

        System.out.println("-------------------------------");

        Method setName = clazz.getMethod("setName", String.class);
        Dog method = (Dog) clazz.newInstance();
        setName.invoke(method, "小姐");
        System.out.println(method.toString());


    }

    @org.junit.Test
    public void test2() {
        Object obj = new Object();
        WeakReference<Object> weakReference = new WeakReference(obj);


        //SoftReference<Object> softReference=new SoftReference<>(obj);


        System.out.println(5 & 11);
    }

    @org.junit.Test
    public void clazzloader() {
        //获取加载classpath的加载器AppClassloader
        ClassLoader classLoader = Test.class.getClassLoader();
        //输出加载名称
        System.out.println("加载classpath下的类的加载器" + classLoader.toString());
        //输出父加载名称ExtClassLoader
        System.out.println(classLoader.toString() + "的父类加载器" + classLoader.getParent().toString());
        //输出父加载名称的父加载器BootstrapClassLoader，注意这里是null,因为BootstrapClassLoader是C++编写的我们获取不到
        System.out.println(classLoader.getParent().toString() + "的父类加载器" + classLoader.getParent().getParent());
    }

    @org.junit.Test
    public void clazzloader2() throws Exception {
        Class<Test> testClass = Test.class;
        Class<?> aClass = Class.forName("com.example.reflectLearn.test.Test");
        Test test = new Test();
        test.getClass();
    }
}
