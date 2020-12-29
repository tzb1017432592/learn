package com.example.springLearn;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@SpringBootTest
@RunWith(SpringRunner.class)
@Component
class LearnApplicationTests {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void set() {
        redisTemplate.opsForValue().set("test:set", "testValue1");
    }

    private static Selector selector;

    public static void main(String[] args) throws IOException, InterruptedException {
        startSelectorThread();
        while (true) {
            Thread.sleep(2000);
            System.out.println("开始wakeup");
            selector.wakeup();
        }
    }

    private static void startSelectorThread() {
        new Thread(() -> {
            try {
                String host = "127.0.0.1";
                int port = 8888;
                ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
                serverSocketChannel.configureBlocking(false);
                SocketAddress socketAddress = new InetSocketAddress(host, port);
                serverSocketChannel.bind(socketAddress);
                selector = Selector.open();

                serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
                System.out.println("selector start select .....");
                while (true) {
                    selector.select();
                    System.out.println("selector end select ....");
                    Set<SelectionKey> selectionKeySet = selector.selectedKeys();
                    for (Iterator<SelectionKey> iterator = selectionKeySet.iterator(); iterator.hasNext(); ) {
                        SelectionKey selectionKey = iterator.next();
                        System.out.println(selectionKey.toString());
                        selectionKeySet.remove(selectionKey);
                        ServerSocketChannel serverSocketChannel1 = (ServerSocketChannel) selectionKey.channel();
                        SocketChannel clientChannel = serverSocketChannel1.accept();
                        clientChannel.configureBlocking(false);
                        clientChannel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);
                        iterator.remove();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }).start();

       /* HashMap*/
    }



}
