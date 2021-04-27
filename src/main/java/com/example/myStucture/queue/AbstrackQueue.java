package com.example.myStucture.queue;

public interface AbstrackQueue<T> {
    void enqueue(T element);
    T dequeue();
    int size();
    boolean isEmpty();
    T first();
}
