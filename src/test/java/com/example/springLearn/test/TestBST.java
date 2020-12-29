package com.example.springLearn.test;

public class TestBST<E extends Comparable<E>> {
    private class Node{
        public Node right;
        public Node left;
        public E e;

        public Node(E e) {
            this.e=e;
            right=null;
            left=null;
        }

    }
    private Node root;
    private int size;

    public TestBST(Node root, int size) {
        this.root = root;
        this.size = size;
    }

    public TestBST() {
        root=null;
        size=0;
    }

    public void add(E e){
        add(root,e);
    }

    public Node add(Node node,E e){
        if (node==null){
            size++;
            return new Node(e);
        }
        if (e.compareTo(node.e)>0){
            node.right=add(node.right,e);
        }
        if (e.compareTo(node.e)<0){
            node.left=add(node.left,e);
        }
        return node;
    }
}
