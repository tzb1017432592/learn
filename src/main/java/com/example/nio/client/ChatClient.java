package com.example.nio.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.Set;

public class ChatClient {

    private static final String DEFAULT_SERVER_HOST = "127.0.0.1";
    private static final int DEFAULT_SERVER_PORT = 8888;
    private static final String QUIT = "quit";
    private static final int BUFFER = 1024;

    private String host;
    private int port;
    private SocketChannel client;
    private ByteBuffer rBuffer = ByteBuffer.allocate(BUFFER);
    private ByteBuffer wBuffer = ByteBuffer.allocate(BUFFER);
    private Selector selector;
    private Charset charset = Charset.forName("UTF-8");

    public ChatClient() {
        this(DEFAULT_SERVER_HOST, DEFAULT_SERVER_PORT);
    }

    public ChatClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public boolean readyToQuit(String msg) {
        return QUIT.equals(msg);
    }

    private void close(Selector closable) {
        if (closable != null) {
            try {
                closable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void start() {
        try {
            client = SocketChannel.open();
            client.configureBlocking(false);
            client.connect(new InetSocketAddress(host, port));
            selector = Selector.open();
            //注册连接监听事件
            client.register(selector, SelectionKey.OP_CONNECT);
            Set<SelectionKey> selectionKeys;
            while (true) {
                selector.select();
                selectionKeys= selector.selectedKeys();
                for (SelectionKey key : selectionKeys) {
                    handles(key);
                }
                selectionKeys.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClosedSelectorException e) {
            // 用户正常退出
        } finally {
            close(selector);
        }

    }
    /*
    * 由于我们使用的SocketChannel处于非阻塞模式，当调用connect()方法时，调用会立即返回，
    * 但是连接的过程还在进行，需要后续调用finishConnect()方法来完成连接过程。
    * 在连接过程已经启动，但尚未完成之前，isConnectionPending()会返回true，
    * 这就是我们此时在检测的状态。如果连接未能正常创建，
    * 调用finishConnect()则会抛出IOException异常，标志着连接失败。
    *
    * */

    private void handles(SelectionKey key) throws IOException {
        // CONNECT事件 - 连接就绪事件
        if (key.isConnectable()) {
            SocketChannel client = (SocketChannel) key.channel();
            if (client.isConnectionPending()) {
                //调用finishConnect()方法来完成连接过程
                client.finishConnect();
                // 处理用户的输入
                new Thread(new UserInputHandler(this)).start();
            }
            client.register(selector, SelectionKey.OP_READ);
        }
        // READ事件 -  服务器转发消息
        else if (key.isReadable()) {
            SocketChannel client = (SocketChannel) key.channel();
            String msg = receive(client);
            if (msg.isEmpty()) {
                close(selector);
            } else {
                System.out.println(msg);
            }
        }
    }

    public void send(String msg) throws IOException {
        if (msg.isEmpty()) {
            return;
        }
        wBuffer.clear();
        wBuffer.put(charset.encode(msg));
        wBuffer.flip();
        while (wBuffer.hasRemaining()) {
            client.write(wBuffer);
        }
        // 检查用户是否准备退出
        if (readyToQuit(msg)) {
            close(selector);
        }
    }

    private String receive(SocketChannel client) throws IOException {
        rBuffer.clear();
        while (client.read(rBuffer) > 0);
        rBuffer.flip();
        return String.valueOf(charset.decode(rBuffer));
    }

    class UserInputHandler implements Runnable {

        private ChatClient chatClient;

        public UserInputHandler(ChatClient chatClient) {
            this.chatClient = chatClient;
        }

        @Override
        public void run() {
            try {
                // 等待用户输入消息
                BufferedReader r =
                        new BufferedReader(new InputStreamReader(System.in));
                while (true) {
                    String input = r.readLine();
                    // 向服务器发送消息
                    chatClient.send(input);
                    // 检查用户是否准备退出
                    if (chatClient.readyToQuit(input)) {
                        break;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ChatClient client = new ChatClient("127.0.0.1", 8888);
        client.start();
    }
}
