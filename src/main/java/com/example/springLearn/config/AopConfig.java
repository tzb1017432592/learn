package com.example.springLearn.config;

import com.example.springLearn.aop.AspectLogs;
import com.example.springLearn.service.AopService;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

@EnableAspectJAutoProxy
@Configuration
@Import({AopService.class, AspectLogs.class})
public class AopConfig {
    @Test
    public void test1() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AopConfig.class);
        AopService aopService = applicationContext.getBean(AopService.class);
        aopService.testAop();

    }
}
