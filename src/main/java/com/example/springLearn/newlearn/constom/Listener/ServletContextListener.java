package com.example.springLearn.newlearn.constom.Listener;

import org.springframework.stereotype.Component;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

@Component
class MyServletContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("servlet的监听器contextInitialized=============");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("servlet的监听器contextDestroyed================");

    }
}
