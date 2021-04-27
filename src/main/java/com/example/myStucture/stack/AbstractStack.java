package com.example.myStucture.stack;

public interface AbstractStack<T> {
    void push(T t);
    T pop();
    T peek();
    boolean isEmpty();
    int size();
}
