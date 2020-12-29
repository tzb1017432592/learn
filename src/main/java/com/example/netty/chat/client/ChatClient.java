package com.example.netty.chat.client;


import com.example.netty.coderutils.MsgpackDecoder;
import com.example.netty.coderutils.MsgpackEncoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

public class ChatClient {
    private String host;
    private int port;

    public ChatClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void start() {
        EventLoopGroup works = new NioEventLoopGroup();
        try {
            Bootstrap boot = new Bootstrap();
            boot.group(works).channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel sc) throws Exception {
                            ChannelPipeline pipeline = sc.pipeline();
                            pipeline.addLast("line", new LineBasedFrameDecoder(1024));
                            pipeline.addLast("encode", new StringEncoder(CharsetUtil.UTF_8));//编码器
                            pipeline.addLast("decode", new StringDecoder(CharsetUtil.UTF_8));//解码器
                            pipeline.addLast(new ClientHandler());
                        }
                    });
            ChannelFuture future = boot.connect(this.host, this.port).sync();
            System.out.println("客户端已经连接");
            future.channel().closeFuture().sync();
            System.out.println("客户端已经关闭");

        } catch (Exception e) {

        } finally {
            works.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        new ChatClient("localhost", 8888).start();
    }
}
