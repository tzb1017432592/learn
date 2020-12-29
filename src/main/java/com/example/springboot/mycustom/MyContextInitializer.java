package com.example.springboot.mycustom;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;

import java.util.HashMap;
import java.util.Map;

@Order(1)
public class MyContextInitializer implements
        ApplicationContextInitializer<ConfigurableApplicationContext> {
    @Override
    public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
        ConfigurableEnvironment environment = configurableApplicationContext.getEnvironment();
        Map<String, Object> hashMap = new HashMap<>();
        hashMap.put("key1", "myInitialize");
        MapPropertySource mapPropertySource = new MapPropertySource("myInitializer", hashMap);
        environment.getPropertySources().addLast(mapPropertySource);
        System.out.println("我的系统初始化器");

    }
}
