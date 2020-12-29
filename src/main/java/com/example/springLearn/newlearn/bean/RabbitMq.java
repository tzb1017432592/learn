package com.example.springLearn.newlearn.bean;

import lombok.Data;

@Data
public class RabbitMq {
    private String ip;
    private int port;
    private String username;
    private String password;
    private String virtualHost;

    public RabbitMq() {
    }

    public RabbitMq(String ip, int port, String username, String password, String virtualHost) {
        this.ip = ip;
        this.port = port;
        this.username = username;
        this.password = password;
        this.virtualHost = virtualHost;
    }
}
