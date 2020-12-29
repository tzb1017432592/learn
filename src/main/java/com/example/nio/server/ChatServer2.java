package com.example.nio.server;

import java.io.Closeable;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ChatServer2 {

    private static final int DEFAULT_PORT = 8888;
    private static final String QUIT = "quit";
    private static final int BUFFER = 1024;

    private ServerSocketChannel server;
    //多路复用器
    private Selector selector;
    private ByteBuffer rBuffer = ByteBuffer.allocate(BUFFER);
    private ByteBuffer wBuffer = ByteBuffer.allocate(BUFFER);
    private Charset charset = Charset.forName("UTF-8");
    private int port;

    public ChatServer2() {
        this(DEFAULT_PORT);
    }

    public ChatServer2(int port) {
        this.port = port;
    }

    private void start() {
        try {
            server = ServerSocketChannel.open();
            selector = Selector.open();
            //设置非阻塞通道
            server.configureBlocking(true);
            server.socket().bind(new InetSocketAddress(port));
            System.out.println("启动服务器2， 监听端口：" + port + "...");
            Set<SelectionKey> selectionKeys = null;
            //注册通道的读监听事件
            CopyOnWriteArrayList<SocketChannel> cwls = new CopyOnWriteArrayList<>();
            new Thread(() -> {
                try {
                        SocketChannel accept = server.accept();
                        cwls.add(accept);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
            while (true) {
                selector.selectNow();
                Iterator<SocketChannel> iterator1 = cwls.iterator();
                while (iterator1.hasNext()){
                    SocketChannel next = iterator1.next();
                    next.configureBlocking(false);
                    next.register(selector, SelectionKey.OP_READ);
                    System.out.println(getClientName(next) + "已连接");
                    iterator1.remove();
                }
                selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()) {
                    handles(iterator.next());
                    iterator.remove();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close(selector);
        }

    }

    private void handles(SelectionKey key) throws IOException {

        // READ事件 - 客户端发送了消息
        if (key.isReadable()) {
            SocketChannel client = (SocketChannel) key.channel();
            //读取客户端消息
            String fwdMsg = receive(client);
            if (fwdMsg.isEmpty()) {
                // 取消这个通道的监听事件
                key.cancel();
                //通知多路复用器重新调整监听
                selector.wakeup();
            } else {
                System.out.println(getClientName(client) + ":" + fwdMsg);
                //消息转发
                forwardMessage(client, fwdMsg);
                // 检查用户是否退出
                if (readyToQuit(fwdMsg)) {
                    key.cancel();
                    selector.wakeup();
                    System.out.println(getClientName(client) + "已断开");
                }
            }

        }
    }

    //将其他客户的消息转发给客户端
    private void forwardMessage(SocketChannel client, String fwdMsg) throws IOException {
        for (SelectionKey key : selector.keys()) {
            Channel connectedClient = key.channel();
            if (connectedClient instanceof ServerSocketChannel) {
                continue;
            }
            //key.isValid()判断SelectionKey是否有效，
            //client.equals(connectedClient) 判断消息不是这个客户端发的，避免自己给自己发消息
            if (key.isValid() && !client.equals(connectedClient)) {
                wBuffer.clear();
                //写消息到Buffer中
                wBuffer.put(charset.encode(getClientName(client) + ":" + fwdMsg));
                //切换到读状态
                wBuffer.flip();
                //判断Buffer中是否还残存数据
                while (wBuffer.hasRemaining()) {
                    //将Buffer的数据写入通道
                    ((SocketChannel) connectedClient).write(wBuffer);
                }
            }
        }
    }

    //接收客户端消息
    private String receive(SocketChannel client) throws IOException {
        //先清空缓冲区的消息
        rBuffer.clear();
        //将通道的消息读到缓冲区
        while (client.read(rBuffer) > 0) ;
        //切换到读模式
        rBuffer.flip();
        //从缓冲区读数据
        return String.valueOf(charset.decode(rBuffer));
    }

    private String getClientName(SocketChannel client) {
        return "客户端[" + client.socket().getPort() + "]";
    }

    private boolean readyToQuit(String msg) {
        return QUIT.equals(msg);
    }

    private void close(Closeable closable) {
        if (closable != null) {
            try {
                closable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ChatServer2 chatServer = new ChatServer2(8888);
        chatServer.start();
    }
}
