package com.example.springLearn.test;

public class BST<E extends Comparable<E>> {
    private class Node {
        public E e;
        public Node left, rigth;

        public Node() {

        }

        public Node(E e) {
            this.e = e;
        }
    }

    private Node root;

    private int size;

    private Node add(Node node, E e) {
        if (node == null) {
            size++;
            return new Node(e);
        }
        if (e.compareTo(node.e) == 0) {
            return node;
        } else if (e.compareTo(node.e) < 0) {
            node.left = add(node.left, e);
        } else {
            node.rigth = add(node.rigth, e);
        }
        return node;
    }

    public void add(E e) {
        root = add(root, e);
    }

    private void preOrder(Node node) {
        if (node == null) {
            return;
        }
        System.out.println(node.e);
        preOrder(node.left);
        preOrder(node.rigth);
    }

    public void preOrder() {
        preOrder(root);
    }

    private void inOrder(Node node) {
        if (node == null) {
            return;
        }
        inOrder(node.left);
        System.out.println(node.e);
        inOrder(node.rigth);
    }

    public void inOrder() {
        inOrder(root);
    }

    private void postOrder(Node node) {
        if (node == null) {
            return;
        }
        postOrder(node.left);
        postOrder(node.rigth);
        System.out.println(node.e);
    }

    public void postOrder() {
        postOrder(root);
    }

    private Node min(Node node) {
        if (node.left == null) {
            return node;
        }
        return min(node.left);
    }

    public E min() {
        if (root == null) {
            return null;
        }
        return min(root).e;
    }

    private boolean contains(Node node, E e) {
        if (node==null){
            return false;
        }
        if (e.compareTo(node.e) == 0) {
            return true;
        } else if (e.compareTo(node.e) < 0) {
            return contains(node.left, e);
        } else {
            return contains(node.rigth,e);
        }
    }

    public boolean contains(E e){
        return contains(root,e);
    }
}
