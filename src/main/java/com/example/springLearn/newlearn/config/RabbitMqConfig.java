package com.example.springLearn.newlearn.config;

import com.example.springLearn.newlearn.bean.RabbitMq;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

@Configuration
@PropertySource("classpath:/myApplication.properties")
@ComponentScan(value = "com.example.springLearn.newlearn")
public class RabbitMqConfig {
    @Value("${rabbitmq.id}")
    private String ip;
    @Value("#{5673-1}")
    private int port;
    @Value("${rabbitmq.username}")
    private String username;
    @Value("${rabbitmq.password}")
    private String password;
    @Value("我的virtualHost")
    private String virtualHost;

    @Bean
    public RabbitMq gatRabbbitMq() {
        try {
            System.out.println(ip + ":" + port + ":" + username + ":" + password + ":" + virtualHost);
            RabbitMq rabbitMq = new RabbitMq(ip, port, username, password, virtualHost);
            return rabbitMq;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
