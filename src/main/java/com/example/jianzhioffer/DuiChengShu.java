package com.example.jianzhioffer;

/**
 * @author tianzhoubing
 * @date 2021/5/20 16:57
 * @description
 * 请实现一个函数，用来判断一棵二叉树是不是对称的。如果一棵二叉树和它的镜像一样，那么它是对称的。
 *
 * 例如，二叉树[1,2,2,3,4,4,3] 是对称的。
 *    1
 *  /  \
 *  2   2
 * / \ / \
 * 3 4 4 3
 *
 *
 * 但是下面这个[1,2,2,null,3,null,3] 则不是镜像对称的:
 *   1
 *  / \
 *  2  2
 *  \  \
 *  3  3

 *
 **/
public class DuiChengShu {
    public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }
    public boolean isSymmetric(TreeNode root) {
        return root == null || recur(root.left, root.right);
    }

    private boolean recur(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        if (left == null || right == null || left.val!= right.val) {
            return false;
        }
        return recur(left.left, right.right) && recur(left.right, right.left);
    }
}
