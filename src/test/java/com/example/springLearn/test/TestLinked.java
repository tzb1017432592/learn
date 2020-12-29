package com.example.springLearn.test;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import org.apache.jute.Index;

public class TestLinked<E> {
    private class Node<E>{
        public E e;
        public Node<E> next;

        public Node() {
        }

        public Node(Node<E> next, E e) {
            this.e=e;
            this.next=next;
        }
    }

    private Node<E> dummy;

    private int size;

    public TestLinked() {
        dummy = new Node<>();
        size = 0;
    }
    public void linkedLast(E e){
        add(size,e);
    }

    public void linkedFirst(E e){
        add(0,e);
    }

    public void add(int index, E e){
        if (index<0||index>size){
            throw new IllegalArgumentException("Add failed. Illegal index.");
        }
        Node cur=dummy;
        for (int i=0;i<index;i++){
            cur=cur.next;
        }
        Node next=new Node(cur.next,e);
        cur.next=next;
        size++;
    }
    public E get(int index){
        if (index<0||index>size){
            throw new IllegalArgumentException("Add failed. Illegal index.");
        }
        Node<E> cur=dummy;
        for (int i=0;i<index;i++){
            cur=cur.next;
        }
        return cur.next!=null?cur.next.e:null;
    }

    public boolean contain(E e){
        Node<E> cur=dummy;
        for (int i=0;i<size;i++){
            if (cur.e.equals(dummy)){
                return true;
            }
            cur=cur.next;
        }
        return false;
    }

    public E remove(int index){
        if (index<0||index>size){
            throw new IllegalArgumentException("Add failed. Illegal index.");
        }
        Node<E> cur=dummy;
        for (int i=0;i<index;i++){
            cur=cur.next;
        }
        Node<E> remove=cur.next;
        cur.next=remove.next;
        remove.next=null;
        size--;
        return remove.e;
    }
}
