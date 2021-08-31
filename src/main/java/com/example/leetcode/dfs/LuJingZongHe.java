package com.example.leetcode.dfs;

/**
 * 路径总和
 *
 * 给你二叉树的根节点 root 和一个表示目标和的整数 targetSum ，判断该树中是否存在 根节点到叶子节点 的路径，
 * 这条路径上所有节点值相加等于目标和 targetSum 。
 * 叶子节点 是指没有子节点的节点。
 *
 * 示例 1：
 * 输入：root = [5,4,8,11,null,13,4,7,2,null,null,null,1], targetSum = 22
 * 输出：true
 *
 * 示例 2：
 * 输入：root = [1,2,3], targetSum = 5
 * 输出：false
 *
 * 示例 3：
 * 输入：root = [1,2], targetSum = 0
 * 输出：false
 *
 */
public class LuJingZongHe {
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
    public boolean hasPathSum(TreeNode root, int targetSum) {
        return dfs(root,targetSum);
    }

    private boolean dfs(TreeNode root, int targetSum) {
        if (root==null){
            return false;
        }
        if (root.left==null&&root.right==null){
            return targetSum == root.val;
        }
        return dfs(root.left, targetSum-root.val) || dfs(root.right,targetSum-root.val);
    }
}
