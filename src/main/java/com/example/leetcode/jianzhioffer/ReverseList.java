package com.example.leetcode.jianzhioffer;

/**
 * @author tianzhoubing
 * @date 2021/5/20 15:18
 * @description
 *
 * 链表翻转
 **/
public class ReverseList {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode reverseList(ListNode head) {
        ListNode current = head;
        ListNode next = null;
        ListNode prev = null;
        if (head == null || head.next == null) {
            return head;
        }
        while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            head = current;
            current = next;
        }
        return head;
    }
}
