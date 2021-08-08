package com.example.leetcode.hot;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你二叉树的根结点 root ，请你将它展开为一个单链表：
 * <p>
 * 展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
 * 展开后的单链表应该与二叉树 先序遍历 顺序相同。
 *  
 */
public class TreeToLinkedlist {

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
    public void flatten(TreeNode root) {
        List<TreeNode> list = new ArrayList<>();
        preOrder(root,list);
        for (int i = 1; i < list.size(); i++) {
            TreeNode pre = list.get(i - 1);
            TreeNode cur = list.get(i);
            pre.left=null;
            pre.right=cur;
        }
    }

    public void preOrder(TreeNode root,List<TreeNode> list){
        if (root==null){
            return;
        }
        list.add(root);
        preOrder(root.left,list);
        preOrder(root.right,list);
    }
}
