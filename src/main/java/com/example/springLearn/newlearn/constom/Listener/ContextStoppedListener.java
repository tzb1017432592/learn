package com.example.springLearn.newlearn.constom.Listener;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextStoppedEvent;
import org.springframework.stereotype.Component;

@Component
public class ContextStoppedListener implements ApplicationListener<ContextStoppedEvent> {
    @Override
    public void onApplicationEvent(ContextStoppedEvent event) {
        System.out.println("ContextStoppedListener监听容器关闭的方法================");
    }
}
