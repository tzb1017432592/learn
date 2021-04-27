package com.example.myStucture.queue;

public class QueueEmptyException extends RuntimeException {
    public QueueEmptyException() {
        super("队列为空！！！！");
    }
    public QueueEmptyException(String message) {
        super(message);
    }
}
