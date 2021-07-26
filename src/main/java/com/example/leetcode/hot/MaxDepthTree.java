package com.example.leetcode.hot;

/**
 * @author tianzhoubing
 * @date 2021/7/26 18:52
 * @description 给定一个二叉树，找出其最大深度。
 * <p>
 * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 **/
public class MaxDepthTree {
    class TreeNode {
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
    int maxResult=0;
    public int maxDepth(TreeNode root) {
        if (root == null){
            return 0;
        }
        int maxDepth = 1;
        deepLoop(root.left,maxDepth);
        deepLoop(root.right,maxDepth);
        return maxResult;
    }
    public int deepLoop(TreeNode root,int maxDepth){
        if (root == null) {
            maxResult=Math.max(maxDepth, maxResult);
            return maxDepth;
        }
        maxDepth++;
        deepLoop(root.left,maxDepth);
        deepLoop(root.right, maxDepth);
        return maxDepth;
    }
}
