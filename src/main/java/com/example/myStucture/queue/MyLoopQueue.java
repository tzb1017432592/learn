package com.example.myStucture.queue;

public class MyLoopQueue<E> {
    private E[] data;
    private int front, tail;
    private int size;
    private final static int default_capacity = 10;

    public int getCapacity() {
        return data.length - 1;
    }

    public MyLoopQueue() {
        data = (E[]) new Object[default_capacity];
    }

    public MyLoopQueue(int capacity) {
        data = (E[]) new Object[capacity];
        front = 0;
        tail = 0;
        size = 0;
    }

    public void enqueue(E e) {
        if ((tail + 1) % data.length == front) {
            resize(getCapacity() * 2);
        }
        data[tail] = e;
        tail = (tail + 1) % data.length;
        size++;
    }

    public E dequeue() {
        if (isEmpty())
            throw new IllegalArgumentException("Cannot dequeue from an empty queue.");
        E ret;
        ret = data[front];
        data[front] = null;
        size--;
        front = (front + 1) % data.length;
        if (size == getCapacity() / 4 && getCapacity() / 2 != 0) {
            resize(getCapacity() / 2);
        }
        return ret;
    }

    private boolean isEmpty() {
        return front == tail;
    }

    private void resize(int capacity) {
        E[] newdata = (E[]) new Object[capacity + 1];
        for (int i = 0; i < size; i++) {
            newdata[i] = data[(front + 1) % data.length];
        }
        data = newdata;
        front = 0;
        tail = size;
    }
}
