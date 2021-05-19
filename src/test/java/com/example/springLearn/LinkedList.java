package com.example.springLearn;

public class LinkedList<E> {

    private class Node {
        public E e;
        public Node next;

        public Node(E e) {
            this.e = e;
        }

        public Node() {

        }
    }

    public LinkedList() {
        dummyHead = new Node();
        size = 0;
    }

    private Node dummyHead;
    private int size;

    public void reverList() {
        Node current = dummyHead.next;
        Node next = null;
        Node prev = null;
        if (current != null && current.next != null) {
            return;
        }
        while (current != null) {
            next = current.next; //获取当前节点的下一个节点
            current.next = prev; //将当前节点的next指向前节点
            prev = current; //下一轮循环中，当前节点就是下一轮循环的前节点
            current = next; //前节点的下一个节点是下一轮的当前节点
        }
        dummyHead.next = next;
    }

    private void add(int index, E e) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("参数错误");
        }
        Node pre = dummyHead;
        for (int i = 0; i < index; i++) {
            pre = pre.next;
        }
        pre.next = new Node(e);
        size++;
    }

    public void addFirst(E e) {
        add(0, e);
    }

    public void addLast(E e) {
        add(size, e);
    }

    public E remove(int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("参数错误");
        }
        Node pre = dummyHead;
        for (int i = 0; i < size; i++) {
            pre = pre.next;
        }
        Node remove = pre.next;
        pre.next = remove.next;
        remove.next = null;
        size--;
        return remove.e;
    }

    public void removeElement(E e) {
        Node pre = dummyHead;
        while (pre.next != null) {
            if (pre.next.e.equals(e)) {
                break;
            }
            pre = pre.next;
        }
        if (pre.next != null) {
            Node renode = pre.next;
            pre.next = renode.next;
            renode.next = null;
        }
    }

    public void removeLast() {
        remove(size);
    }

    public void removeFirst() {
        remove(0);
    }
}
