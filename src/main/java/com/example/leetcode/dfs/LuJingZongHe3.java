package com.example.leetcode.dfs;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 路径总和3
 *给定一个二叉树的根节点 root ，和一个整数 targetSum ，求该二叉树里节点值之和等于 targetSum 的 路径 的数目。
 *
 * 路径 不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
 *
 *  
 *
 * 示例 1：
 * 输入：root = [10,5,-3,3,2,null,11,3,-2,null,1], targetSum = 8
 *
 * 输出：3
 * 解释：和等于 8 的路径有 3 条，如图所示。
 *
 * 示例 2：
 * 输入：root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
 * 输出：3
 *
 */
public class LuJingZongHe3 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
    int res = 0;
    public int pathSum(TreeNode root, int targetSum) {
        if (root!=null&&root.left==null&&root.right == null&&root.val==targetSum){
            return 1;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode peek = queue.poll();
            dfs(peek, targetSum);
            if (peek.left!=null){
                queue.offer(peek.left);
            }
            if (peek.right!=null){
                queue.offer(peek.right);
            }
        }
        return res;
    }

    private void dfs(TreeNode root, int targetSum) {
        if (root==null){
            return;
        }
        if (targetSum==0){
            res++;
            return;
        }
        targetSum-=root.val;
        dfs(root.left,targetSum);
        dfs(root.right, targetSum);
    }
}
