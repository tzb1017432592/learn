package com.example.leetcode.jianzhioffer;

import java.util.Stack;

/**
 * @author tianzhoubing
 * @date 2021/5/20 14:19
 * @description
 *
 * 输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。
 **/
public class DaoShuDaYing {
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
        }
    }
    public int[] reversePrint(ListNode head) {
        if (head==null){
            return new int[0];
        }
        ListNode current = head;
        Stack<Integer> stack=new Stack<>();
        while (current!=null){
            stack.push(current.val);
            current=current.next;
        }
        int size = stack.size();
        int[] res=new int[size];

        for (int i = 0; i < size; i++) {
            res[i] = stack.pop();
        }
        return res;
    }
}
