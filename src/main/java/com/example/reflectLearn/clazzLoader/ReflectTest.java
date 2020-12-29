package com.example.reflectLearn.clazzLoader;

import com.example.reflectLearn.bean.MyTest;
import com.example.reflectLearn.bean.ReflectBean;
import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.zip.DeflaterOutputStream;

public class ReflectTest {
    @Test
    public void test() throws Exception {
        // ReflectBean reflectBean=new ReflectBean();
        System.out.println("使用无参构造创建对象");
        Class<ReflectBean> clazz = ReflectBean.class;
        ReflectBean without = clazz.newInstance();//反射使用无参构造创建对象

        System.out.println("\n调用有参构造方法创建对象");
        Constructor<ReflectBean> dCr =
                clazz.getDeclaredConstructor(String.class, String.class);
        Object o = dCr.newInstance("我是name",
                "我是nickname");//创建有参构造方法创建对象
        ReflectBean reflectBean = (ReflectBean) o;
        System.out.println(reflectBean.toString());

        System.out.println("\n调用非私有有方法");
        Method publicSay = clazz.getDeclaredMethod("toString");
        publicSay.invoke(o);

        System.out.println("\n调用私有方法");
        Method privateSay = clazz.getDeclaredMethod("say", String.class);
        privateSay.setAccessible(true);//设置为true，代表可以访问私有
        privateSay.invoke(o, "我是反射调用的私有方法");


        System.out.println("\n调用私有成员变量");
        Field name = clazz.getDeclaredField("name");
        name.setAccessible(true);
        String getname = (String) name.get(o);
        System.out.println(getname);

        System.out.println("\n调用所有的方法和类成员变量");
        Method[] declaredMethods = clazz.getDeclaredMethods();//获取所有的方法
        for (Method declaredMethod : declaredMethods) {
            System.out.println(declaredMethod.getName());
        }
        Field[] declaredFields = clazz.getDeclaredFields();//获取所有的成员变量
        name.setAccessible(true);
        for (Field field : declaredFields) {
            System.out.println(field.getName());
        }
    }

    @Test
    public void test2() throws Exception {
        Class<ReflectBean> clazz = ReflectBean.class;
        System.out.println("获取类上的注解");
        if (clazz.isAnnotationPresent(MyTest.class)) {
            MyTest annotation = clazz.getAnnotation(MyTest.class);
            System.out.println(String.format("value:%s,name:%s",
                    annotation.value(), annotation.name()));
        }

        System.out.println("\n获取方法上的注解");
        Method[] declaredMethods = clazz.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            declaredMethod.setAccessible(true);
            if (declaredMethod.isAnnotationPresent(MyTest.class)) {
                MyTest annotation = declaredMethod.getAnnotation(MyTest.class);
                System.out.println(String.format("value:%s,name:%s",
                        annotation.value(), annotation.name()));
            }
        }

        System.out.println("\n获取成员变量上的注解");
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            declaredField.setAccessible(true);
            if (declaredField.isAnnotationPresent(MyTest.class)) {
                MyTest annotation = declaredField.getAnnotation(MyTest.class);
                System.out.println(String.format("value:%s,name:%s",
                        annotation.value(), annotation.name()));
            }
        }


    }
}
