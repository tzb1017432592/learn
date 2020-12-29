package com.example.netty.chat.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Scanner;

public class ClientHandler extends SimpleChannelInboundHandler<String> {
  /*  @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object s) throws Exception {
        System.out.println((String) s);
    }*/
  @Override
  protected void channelRead0(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
      System.out.println(s);
  }
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        new Thread(()-> {
                Scanner scanner = new Scanner(System.in);
                while (scanner.hasNextLine()) {
                    String s = scanner.nextLine()+"\n";
                    ByteBuf buffer = Unpooled.buffer(s.length());
                    buffer.writeBytes(s.getBytes());
                    ctx.writeAndFlush(buffer);
                }
        }).start();
    }
}
