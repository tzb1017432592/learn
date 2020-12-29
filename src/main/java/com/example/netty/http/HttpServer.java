package com.example.netty.http;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class HttpServer {

    private int port;
    private static final int DEFAULT_PORT = 8888;

    public HttpServer() {
        this(DEFAULT_PORT);
    }

    public HttpServer(int port) {
        this.port = port;
    }

    public void start() {
        // 定义一对线程组
        // 主线程组, 用于接受客户端的连接，但是不做任何处理，跟老板一样，不做事
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        // 从线程组, 老板线程组会把任务丢给他，让手下线程组去做任务
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            // netty服务器的创建, ServerBootstrap 是一个启动类
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            // 设置主从线程组
            serverBootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new HttpServerInitializer());
            // 启动server，并且设置8088为启动的端口号，同时启动方式为同步
            ChannelFuture channelFuture = serverBootstrap.bind(port).sync();
            // 监听关闭的channel，设置位同步方式
            System.out.println("服务已经启动...............");
            channelFuture.channel().closeFuture().sync();
            System.out.println("服务已经关闭...............");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        HttpServer httpServer = new HttpServer(8888);
        httpServer.start();
    }
}
