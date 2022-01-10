package com.example.leetcode.linked;

/**
 * @author tianzhoubing
 * @date 2022/1/7 10:32
 * @description
 **/
public class OddEvenList {
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

    public ListNode oddEvenList(ListNode head) {
        if (head==null){
            return null;
        }
        ListNode second = head.next;
        ListNode next = head.next;
        ListNode prev = head;
        while (next != null) {
            prev.next = next.next;
            prev = next;
            next = next.next;
        }
        ListNode temp = head;
        while (temp.next!=null){
            temp = temp.next;
        }
        temp.next = second;
        return head;
    }


    public ListNode oddEvenList2(ListNode head) {
        if (head==null){
            return null;
        }
        ListNode evenHead = head.next;
        ListNode odd = head;
        ListNode even = head.next;
        while (even != null && even.next!=null) {
            odd.next = even.next;
            odd = even.next;
            even.next = odd.next;
            even = even.next;
        }
        odd.next = evenHead;
        return head;
    }
}
