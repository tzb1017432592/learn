package com.example.springLearn;

public class LinkedList<E> {

    public class Node{
        public E e;
        public Node next;

        public Node(E e) {
            this.e = e;
        }
        public Node(){

        }
    }
    public LinkedList() {
        dummyHead = new Node();
        size = 0;
    }
    private Node dummyHead;
    private int size;

    public void reverList(){
        Node head = dummyHead.next;
        while (head!=null){
            Node next = head.next;
            if (next==null){
                dummyHead.next=next;
            }
            next.next=head;
            head=next;
        }
    }

    private void add(int index,E e){
        if (index<0||index>size){
            throw new IllegalArgumentException("参数错误");
        }
        Node pre = dummyHead;
        for (int i=0;i<index;i++){
            pre=pre.next;
        }
        pre.next=new Node(e);
        size++;
    }

    public void addFirst(E e){
        add(0,e);
    }

    public void addLast(E e){
        add(size,e);
    }

    public E remove(int index){
        if (index<0||index>size){
            throw new IllegalArgumentException("参数错误");
        }
        Node pre = dummyHead;
        for (int i=0;i<size;i++){
            pre=pre.next;
        }
        Node remove = pre.next;
        pre.next=remove.next;
        remove.next=null;
        size--;
        return remove.e;
    }

    public void removeElement(E e){
        Node pre = dummyHead;
        while (pre.next!=null){
            if (pre.next.e.equals(e)){
                break;
            }
            pre=pre.next;
        }
        if (pre.next!=null){
            Node renode = pre.next;
            pre.next=renode.next;
            renode.next=null;
        }
    }

    public void removeLast(){
       remove(size);
    }

    public void removeFirst(){
       remove(0);
    }
}
