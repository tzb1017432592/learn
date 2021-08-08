package com.example.leetcode.hot;

/**
 * 翻转一棵二叉树。
 * <p>
 * 输入：
 * 4
 * /   \
 * 2     7
 * / \   / \
 * 1   3 6   9
 * <p>
 * 输出：
 * 4
 * /   \
 * 7     2
 * / \   / \
 * 9   6 3   1
 */
public class FanZhuangTree {
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

    public TreeNode invertTree(TreeNode root) {
        if (root==null){
            return null;
        }
        TreeNode left = root.left;
        TreeNode right = root.right;
        root.left=right;
        root.right=left;
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }
}
