package com.example.leetcode.hot;

/**
 * @author tianzhoubing
 * @date 2021/8/24 20:01
 * @description K 个一组翻转链表
 **/
public class KGeReverList {
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

    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode hair = new ListNode(0);
        hair.next = head;
        ListNode pre = hair;
        while (head != null) {
            ListNode tail = pre;
            for (int i = 0; i < k; i++) {
                if (tail.next == null) {
                    return hair.next;
                }
                tail = tail.next;
            }
            ListNode next = tail.next;
            ListNode[] th = myReverse(head, tail);
            head = th[0];
            tail = th[1];
            pre.next = head;
            tail.next = next;
            pre=tail;
            head=tail.next;
        }
        return hair.next;
    }

    private ListNode[] myReverse(ListNode head, ListNode tail) {
        ListNode pre = tail.next;
        ListNode cur = head;
        while (pre != tail) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return new ListNode[]{tail, head};
    }
}
