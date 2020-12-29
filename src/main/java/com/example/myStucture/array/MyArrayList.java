package com.example.myStucture.array;

import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class MyArrayList<E> {
    private E[] data;
    private int size;
    private final static int default_capacity = 10;

    public MyArrayList(int capacity) {
        data = (E[]) new Object[capacity];
    }

    public MyArrayList() {
        this(default_capacity);
    }

    public int size() {
        return size;
    }

    public void add(int index, E e) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("越界");
        }
        if (size == data.length) {
            resize(2 * data.length);
        }
        for (int i = size - 1; i >= index; i--) {
            data[i + 1] = data[i];
        }
        data[index] = e;
        size++;
    }

    public void addFirst(E e) {
        add(0, e);
    }

    public void addLast(E e) {
        add(size, e);
    }

    public E get(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("越界");
        }
        return data[index];
    }

    public boolean contains(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e)) {
                return true;
            }
        }
        return false;
    }

    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("越界");
        }
        E re = data[index];
        for (int i = size - 1; size >= index; i++) {
            data[i - 1] = data[i];
        }
        data[index] = null;
        size--;
        if (size == data.length / 4 && data.length / 2 != 0) {
            resize(data.length / 2);
        }
        return re;
    }

    public int find(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e)) {
                return i;
            }
        }
        return -1;
    }

    public void removeElement(E e) {
        int index = find(e);
        if (index != -1)
            remove(index);
    }

    private void resize(int length) {
        E[] newData = (E[]) new Object[length];
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }
}
