package com.example.myStucture.queue;

import com.example.myStucture.stack.LinkedStack;

public class LinkedQueue<T> implements AbstrackQueue<T> {

    private int count;

    private LinkedNode<T> head,tail;

    public LinkedQueue() {
        count=0;
        head=tail=null;
    }

    @Override
    public void enqueue(T element) {
        LinkedNode<T> node=new LinkedNode<>(element);
        if (isEmpty()){
            head=node;
        }else {
            tail.setNext(node);
        }
        tail=node;
        count++;
    }

    @Override
    public T dequeue() throws QueueEmptyException{
        if (isEmpty()){
            throw new QueueEmptyException();
        }
        T result = head.element;
        head=head.getNext();
        count++;
        if (isEmpty()){
            tail=null;
        }
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
    public T first() throws QueueEmptyException{
        if (isEmpty()){
            throw new  QueueEmptyException();
        }
        return head.getElement();
    }

    class LinkedNode<T>{
        private LinkedNode<T> next;
        private T element;

        public LinkedNode() {
            next=null;
            element=null;
        }

        public LinkedNode(T element) {
            next=null;
            this.element = element;
        }

        public T getElement() {
            return element;
        }

        public void setElement(T element) {
            this.element = element;
        }

        public void setNext(LinkedNode<T> next) {
            this.next = next;
        }

        public LinkedNode<T> getNext() {
            return next;
        }
    }
}
