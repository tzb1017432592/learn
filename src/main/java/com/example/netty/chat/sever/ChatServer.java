package com.example.netty.chat.sever;

import com.example.netty.coderutils.MsgpackDecoder;
import com.example.netty.coderutils.MsgpackEncoder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

public class ChatServer {
    private int port;

    public ChatServer(int port) {
        this.port = port;
    }

    public void start(){
        EventLoopGroup boss=new NioEventLoopGroup();
        EventLoopGroup works=new NioEventLoopGroup();
        try {
            ServerBootstrap boot=new ServerBootstrap();
            boot.group(boss,works)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel sc) {
                            ChannelPipeline pipeline = sc.pipeline();
                            pipeline.addLast("line",new LineBasedFrameDecoder(1024));
                            pipeline.addLast("encode", new StringEncoder(CharsetUtil.UTF_8));//解码器
                            pipeline.addLast("decode", new StringDecoder(CharsetUtil.UTF_8));//编码器
                            pipeline.addLast(new ServerHander());
                        }
                    })
                    .option(ChannelOption.SO_BACKLOG,128)
                    .option(ChannelOption.SO_KEEPALIVE,true);
            ChannelFuture future = boot.bind(this.port).sync();
            System.out.println("服务已经启动...............");
            future.channel().closeFuture().sync();
            System.out.println("服务已经关闭...............");
        }catch (Exception e){

        }finally {
            boss.shutdownGracefully();
            works.shutdownGracefully();
        }

    }

    public static void main(String[] args) {
        new ChatServer(8888).start();
    }
}
