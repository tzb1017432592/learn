package com.example.springLearn.test;

public class Queue<E> {
    private class Node{
        public E e;
        public Node next;
        public Node(E e){
            this.e=e;
        }
    }
    private int size;
    private Node head,tail;
    public void enqueue(E e){
        Node node=new Node(e);
        if (isEmpty()){
            head=node;
        }else {
            tail.next=node;
        }
        tail=node;
        size++;
    }
    public E dequeue(){
        if (isEmpty()){
            return null;
        }
        E e = head.e;
        head=head.next;
        size--;
        if (isEmpty()){
            tail=null;
        }
        return e;
    }
    public boolean isEmpty() {
        return size<0;
    }
}
