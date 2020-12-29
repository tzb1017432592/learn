package com.example.netty.http;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.codec.http.HttpServerCodec;


public class HttpServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        // 通过SocketChannel去获得对应的管道
        ChannelPipeline pipeline = channel.pipeline();

        // 通过管道，添加handler
        // HttpServerCodec是由netty自己提供的助手类，可以理解为拦截器
        // 当请求到服务端，我们需要做解码，响应到客户端做编码
        pipeline.addLast("HttpServerCodec", new HttpServerCodec());
        // http 消息聚合器512*1024为接收的最大contentlength
        pipeline.addLast("httpAggregator",new HttpObjectAggregator(512*1024));

        // server端接收到的是httpRequest，所以要使用HttpRequestDecoder进行解码


        // 添加自定义的助手类，返回 "hello netty~"
        pipeline.addLast("customHandler", new CustomHandler());
    }
}
