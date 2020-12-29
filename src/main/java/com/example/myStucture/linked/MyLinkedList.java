package com.example.myStucture.linked;

public class MyLinkedList<E> {
    private class Node<E> {
        public E e;
        public Node<E> next;
        public Node<E> pre;

        public Node(Node<E> pre, E e, Node<E> next) {
            this.e = e;
            this.next = next;
            this.pre = pre;
        }

        public Node(E e) {
            this(null, e, null);
        }

        public Node() {
            this(null, null, null);
        }

    }

    private Node<E> head;
    private Node<E> dumHead;
    private int size;

    private void checkIndex(int index) {
        if (index > size) {
            throw new IndexOutOfBoundsException("越界");
        }
    }

    public MyLinkedList() {

    }

    public void addFirst(E e) {
        add(0, e);
    }

    public void addLast(E e) {
        add(size, e);
    }

    public void add(int index, E e) {
        checkIndex(index);
        if (dumHead == null) {
            dumHead = new Node();
        }
        Node<E> pre = dumHead;
        for (int i = 0; i < index; i++) {
            pre = pre.next;
        }
        Node<E> newNode = new Node(pre, e, pre.next);
        pre.next = newNode;
    }

    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("越界");
        }
        Node<E> pre = dumHead;
        for (int i = 0; i < index; i++) {
            pre = pre.next;
        }
        return pre.e;
    }

    public E getFirst() {
        return get(0);
    }

    public E getLast() {
        return get(size - 1);
    }

    public boolean contains(E e) {
        Node<E> pre = dumHead;
        for (int i = 0; i < size; i++) {
            pre = pre.next;
            if (pre.e.equals(e)) {
                return true;
            }
        }
        return false;
    }

    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("越界");
        }
        Node<E> cur = dumHead;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        Node<E> pre = cur.pre;
        Node<E> next = cur.next;
        pre.next = next;
        next.pre = pre;
        cur.next = null;
        cur.pre = null;
        size--;
        return cur.e;
    }

    public E removeFirst() {
        return remove(0);
    }

    public E removeLast() {
        return remove(size - 1);
    }

    public int Size() {
        return size;
    }

    public boolean removeElement(E e) {
        Node<E> cur = dumHead;
        for (int i = 0; i < size; i++) {
            cur = cur.next;
            if (cur.e.equals(e)) {
                Node<E> pre = cur.pre;
                Node<E> next = cur.next;
                pre.next = next;
                next.pre = pre;
                cur.next = null;
                cur.pre = null;
                return true;
            }
        }
        return false;
    }
 /*   public void add(int index, E e){
        checkIndex(index);
        Node<E> newNode<E>;
        if (head==null){
            head=new Node<E>();
        }
        Node<E> pre = head;
        if (index==size){
            index=size-1;
            for (int i = 0; i < index; i++){
                pre = pre.next;
            }
            newNode<E> = new Node<E>(pre, e, null);
        }else {
            for (int i = 0; i < index; i++) {
                pre = pre.next;
            }
            newNode<E> = new Node<E>(pre.pre, e, pre);
        }
        pre.next = newNode<E>;
        size++;
    }*/

}
