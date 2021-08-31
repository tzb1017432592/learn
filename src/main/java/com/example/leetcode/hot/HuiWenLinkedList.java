package com.example.leetcode.hot;

import java.util.HashMap;
import java.util.Map;

/**
 * @author tianzhoubing
 * @date 2021/8/10 21:28
 * @description 请判断一个链表是否为回文链表。
 * <p>
 * 输入: 1->2->2->1
 * 输出: true
 **/
public class HuiWenLinkedList {
    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public boolean isPalindrome(ListNode head) {
        if (head == null) {
            return false;
        }
        ListNode half = halfMeth(head);
        ListNode reverse = reverseList(half.next);
        ListNode htemp = head;
        ListNode rtemp = reverse;
        while (rtemp != null) {
            if (htemp.val != rtemp.val) {
                return false;
            }
            htemp = htemp.next;
            rtemp = rtemp.next;
        }
        reverseList(reverse);
        return true;
    }

    public ListNode reverseList(ListNode head) {
        ListNode cur = head;
        ListNode pre = null;
        while (cur != null) {
            ListNode temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        return pre;
    }

    private ListNode halfMeth(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (slow.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}
