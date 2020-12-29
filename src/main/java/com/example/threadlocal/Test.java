package com.example.threadlocal;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test {
   /* public void test(){
        Executors.newFixedThreadPool()
    }*/

   @org.junit.Test
    public void test() {
       int i = 16 * 2 / 3;
       System.out.println(i);
   }
}
