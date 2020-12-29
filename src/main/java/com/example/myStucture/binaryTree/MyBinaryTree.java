package com.example.myStucture.binaryTree;

public class MyBinaryTree<E extends Comparable<E>> {
    private class Node {
        private Node left, right;
        private E e;

        public Node(E e) {
            this.e = e;
            left = null;
            right = null;
        }
    }

    private Node root;
    private int size;

    public MyBinaryTree() {
        root = null;
        size = 0;
    }

    public void add(E e) {
        root = add(root, e);
    }

    private Node add(Node node, E e) {
        if (node == null) {
            size++;
            return new Node(e);
        } else if (e.compareTo(node.e) < 0) {
            node.left = add(node.left, e);

        } else if (e.compareTo(node.e) > 0) {
            node.right = add(node.right, e);
        }
        return node;
    }

    public boolean contains(E e) {
        return contains(root, e);
    }

    private boolean contains(Node root, E e) {
        if (root == null) {
            return false;
        }
        if (e.compareTo(root.e) == 0) {
            return true;
        }
        if (e.compareTo(root.e) < 0) {
            return contains(root.left, e);
        }
        if (e.compareTo(root.e) > 0) {
            return contains(root.right, e);
        }
        return false;
    }

    private Node getKey(Node root, E e) {
        if (root == null) {
            return null;
        }
        if (e.compareTo(root.e) == 0) {
            return root;
        }
        if (e.compareTo(root.e) < 0) {
            return getKey(root.left, e);
        }
        if (e.compareTo(root.e) > 0) {
            return getKey(root.right, e);
        }
        return null;
    }

    public boolean updateKey(Node node, E e) {
        Node key = getKey(root, node.e);
        if (key == null) {
            return false;
        } else {
            key.e = e;
            return true;
        }

    }

    public E getMaxKey() {
        if (root.right == null) {
            return root.e;
        }
        return getMaxKey(root).e;
    }

    private Node getMaxKey(Node root) {
        if (root.right == null) {
            return root;
        }
        return getMaxKey(root.right);
    }

    public E getMinKey() {
        if (root.left == null) {
            return root.e;
        }
        return getMinKey(root).e;
    }

    private Node getMinKey(Node root) {
        if (root.left == null) {
            return root;
        }
        return getMaxKey(root.left);
    }

    public E removeMin() {
        E minKey = getMinKey();
        root = removeMin(root);
        return minKey;
    }

    private Node removeMin(Node root) {
        if (root.left == null) {
            Node right = root.right;
            root.right = null;
            size--;
            return right;
        }
        root.left = removeMin(root.left);
        return root;
    }

    public boolean remove(E e) {
        if (contains(e)) {
            remove(root, e);
            return true;
        }
        return false;
    }

    private Node remove(Node root, E e) {
        if (root == null) {
            return null;
        }
        if (e.compareTo(root.e) < 0) {
            root.left = remove(root.left, e);
        }
        if (e.compareTo(root.e) > 0) {
            root.right = remove(root.right, e);
        } else {
            if (root.left == null) {
                Node right = root.right;
                root.right = null;
                return right;
            }
            if (root.right == null) {
                Node left = root.left;
                root.left = null;
                return left;
            }
            Node newkey = new Node(getMinKey(root.right).e);
            size++;
            newkey.right = removeMin(root.right);
            newkey.left = root.left;
            root.left = root.right = null;
            size--;
            return newkey;
        }
        return root;
    }
}
