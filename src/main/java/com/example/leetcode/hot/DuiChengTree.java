package com.example.leetcode.hot;

/**
 * @author tianzhoubing
 * @date 2021/7/26 17:49
 * @description 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
 * <p>
 *     1
 *    / \
 *   2   2
 *  / \ / \
 * 3  4 4  3
 * <p>
 * <p>
 * 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:
 * <p>
 *    1
 *   / \
 *  2   2
 *   \   \
 *   3    3
 **/
public class DuiChengTree {
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

    public boolean isSymmetric(TreeNode root) {
        if (root==null){
            return true;
        }
        if ((root.right == null && root.left == null))
            return true;
        if ((root.right == null || root.left == null))
            return false;
        return loop(root.left,root.right);
    }

    public boolean loop(TreeNode root1,TreeNode root2){
        if (root1==null&&root2==null){
            return true;
        }
        if (root1 == null||root2 == null||root1.val!=root2.val){
            return false;
        }
        return loop(root1.left, root2.right)
                && loop(root1.right,root2.left);
    }
}
