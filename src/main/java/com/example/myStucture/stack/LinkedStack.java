package com.example.myStucture.stack;

public class LinkedStack<T> implements AbstractStack<T> {

    private LinkedNode<T> top;

    private int count;

    public LinkedStack() {
        top=null;
        count=0;
    }

    @Override
    public void push(T t) {
        LinkedNode<T> node=new LinkedNode<>(t);
        node.setNext(top);
        top=node;
        count++;
    }

    @Override
    public T pop() throws StackEmptyException{
        if (isEmpty()){
            throw new StackEmptyException();
        }
        T element = top.getElement();
        top=top.getNext();
        count--;
        return element;
    }

    @Override
    public T peek() throws StackEmptyException{
        if (isEmpty()){
            throw new StackEmptyException();
        }
        return top.getElement();
    }

    @Override
    public boolean isEmpty() {
        return size()>0?false:true;
    }

    @Override
    public int size() {
        return count;
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
