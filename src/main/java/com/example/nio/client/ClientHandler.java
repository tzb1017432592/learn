package com.example.nio.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class ClientHandler implements Runnable {

    private ChatClient chatClient;

    public ClientHandler(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @Override
    public void run() {
        try {
            // 等待用户输入消息
            BufferedReader consoleReader =
                    new BufferedReader(new InputStreamReader(System.in));
           // Scanner scanner=new Scanner(System.in);
            while (true) {
              //  String input = scanner.nextLine();
                String input = consoleReader.readLine();

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