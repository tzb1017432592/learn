package com.example.myStucture.linked;

public class LinkedListQueue<E> implements Queue<E> {

    private class Node {
        private Node pre, next;
        private E e;

        public Node() {
        }

        public Node(Node pre, E e, Node next) {
            this.pre = pre;
            this.next = next;
            this.e = e;
        }
    }

    private Node tail, head;

    private int siz;

    @Override
    public int getSize() {
        return siz;
    }

    @Override
    public boolean isEmpty() {
        return siz == 0 ? true : false;
    }

    @Override
    public void enqueue(E e) {
        if (tail == null) {
            tail = new Node(null, e, null);
            head = tail;
        } else {
            tail.next = new Node(tail, e, null);
        }
        siz++;
    }

    @Override
    public E dequeue() {
        if (isEmpty())
            throw new IllegalArgumentException("Cannot dequeue from an empty queue.");

        Node cur = head;
        head = head.next;
        cur.next = null;
        siz--;
        if (head == null) {
            head = null;
        }
        return cur.e;
    }

    @Override
    public E getFront() {
        if (isEmpty())
            throw new IllegalArgumentException("Queue is empty.");
        return head.e;
    }
}
