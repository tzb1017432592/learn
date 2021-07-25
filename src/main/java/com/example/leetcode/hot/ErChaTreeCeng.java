package com.example.leetcode.hot;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *
 *
 *    [
 *   [3],
 *   [9,20],
 *   [15,7]
 * ]
 */
public class ErChaTreeCeng {
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
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root==null){
            return new ArrayList<>();
        }
        List<TreeNode> nodeList = new ArrayList<>();
        nodeList.add(root);
        List<List<Integer>> nodeResults = new ArrayList<>();
        List<Integer> integers = new ArrayList<>();
        integers.add(root.val);
        nodeResults.add(integers);
        return levelOrderProccess(nodeResults,nodeList);
    }
    public List<List<Integer>> levelOrderProccess(List<List<Integer>> nodeResults,List<TreeNode> nodeList) {
        List<TreeNode> treeNodeList = new ArrayList<>();
        List<Integer> integers = new ArrayList<>();
        for (TreeNode treeNode : nodeList) {
            if (treeNode.left!=null) {
                treeNodeList.add(treeNode.left);
                integers.add(treeNode.left.val);
            }
            if (treeNode.right!=null){
                treeNodeList.add(treeNode.right);
                integers.add(treeNode.right.val);
            }
        }
        if (treeNodeList.size()>0) {
            nodeResults.add(integers);
            levelOrderProccess(nodeResults,treeNodeList);
        }
        return nodeResults;
    }
}
