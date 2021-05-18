package com.example.springLearn.test;

public class Stack<E> {
    private int size;
    private Node top;
    public void push(E e){
        Node node=new Node(e);
        node.next=top;
        top=node;
        size++;
    }
    public E pop(){
        if (isEmpty()){
            throw new IllegalArgumentException();
        }
        Node reNode=top;
        top=top.next;
        size--;
        return reNode.e;
    }
    public E peek(){
        if (isEmpty()){
            throw new IllegalArgumentException();
        }
        Node reNode=top;
        return reNode.e;
    }
    private boolean isEmpty(){
        return size<0;
    }
    public int size(){
        return size;
    }
    private class Node{
        public E e;
        public Node next;

        public Node(E e) {
            this.e = e;
        }

        public E getE() {
            return e;
        }

        public void setE(E e) {
            this.e = e;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }
}
