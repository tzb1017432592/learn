package com.example.myStucture.queue;

import java.util.Arrays;

public class ArrayQueue<T> implements AbstrackQueue<T> {

    private final static int DEFAULT_CAPACITY=100;
    private int front,rear,count;
    private T[] queque;

    public ArrayQueue() {
        this(DEFAULT_CAPACITY);
        front=rear=count=0;
    }

    public ArrayQueue(int initialCapacity) {
        front=rear=count=0;
        queque= (T[]) new Object[initialCapacity];
    }

    @Override
    public void enqueue(T element) {
        if (size()==queque.length){
            expandCapacity();
        }
        queque[rear]=element;
        rear=(rear+1)%queque.length;
        count++;
    }

    private void expandCapacity() {
        T[] newCapacity= (T[]) new Object[queque.length*2];
        for (int i=0;i<count;i++){
            newCapacity[i]=queque[front];
            front=(front+1)%queque.length;
        }
        front=0;
        rear=count;
    }

    @Override
    public T dequeue() throws QueueEmptyException{
        if (isEmpty()){
            throw new QueueEmptyException();
        }
        T result = queque[front];
        queque[rear]=null;
        front=(front+1)%queque.length;
        count--;
        return result;
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public boolean isEmpty() {
        return size()>0?false:true;
    }

    @Override
    public T first() {
        return queque[front];
    }
}
