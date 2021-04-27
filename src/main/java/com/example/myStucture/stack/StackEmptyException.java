package com.example.myStucture.stack;

public class StackEmptyException extends RuntimeException{
    public StackEmptyException(String message) {
        super(message);
    }

    public StackEmptyException() {
        super("栈为空！！！");
    }
}
