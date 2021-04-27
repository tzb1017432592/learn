package com.example.myStucture.stack;

import java.util.Arrays;

public class ArrayStack<T> implements AbstractStack<T> {

    private final static int DEFAULT_CAPACITY=100;
    T[] stack;
    private int top;

    public ArrayStack() {
        top=0;
        stack= (T[]) new Object[DEFAULT_CAPACITY];
    }

    public ArrayStack(int initalCapacity) {
        top=0;
        stack= (T[]) new Object[initalCapacity];
    }


    @Override
    public void push(T t) {
        if (size()==stack.length){
            expandCapacity();
        }
        stack[top]=t;
        top++;
    }

    private void expandCapacity() {
        Arrays.copyOf(stack,stack.length*2);
    }

    @Override
    public T pop() throws StackEmptyException{
        if (isEmpty()){
            throw new StackEmptyException();
        }
        T result=stack[top];
        stack[top]=null;
        top--;
        return result;
    }

    @Override
    public T peek() throws StackEmptyException {
        if (size()<=0){
            throw new StackEmptyException();
        }
        return stack[top-1];
    }

    @Override
    public boolean isEmpty() {
        return stack.length>0?false:true;
    }

    @Override
    public int size() {
        return stack.length;
    }
}
