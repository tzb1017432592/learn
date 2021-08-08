package com.example.leetcode.hot;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一棵树的前序遍历 preorder 与中序遍历  inorder。请构造二叉树并返回其根节点。
 * <p>
 * Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
 * Output: [3,9,20,null,null,15,7]
 */
public class PreInoGouZaoTree {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    private Map<Integer, Integer> indexMap;

    public TreeNode myBuildTree(int[] preorder, int preorder_left, int preorder_right, int inorder_left) {
        if (preorder_left > preorder_right) {
            return null;
        }
        int inorder_root = indexMap.get(preorder[preorder_left]);
        TreeNode root = new TreeNode(preorder[preorder_left]);
        int size_left_subtree = inorder_root - inorder_left;
        root.left = myBuildTree(preorder,  preorder_left + 1, preorder_left + size_left_subtree, inorder_left);
        root.right = myBuildTree(preorder,  preorder_left + size_left_subtree + 1, preorder_right, inorder_root + 1);
        return root;
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int n = preorder.length;
        indexMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            indexMap.put(inorder[i], i);
        }
        return myBuildTree(preorder, 0, n - 1, 0);
    }

    public static void main(String[] args) {
        PreInoGouZaoTree preInoGouZaoTree = new PreInoGouZaoTree();
        preInoGouZaoTree.buildTree(new int[]{3, 9, 20, 15, 7}, new int[]{9, 3, 15, 20, 7});
    }
}
