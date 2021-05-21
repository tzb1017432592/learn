package com.example.jianzhioffer;

/**
 * @author tianzhoubing
 * @date 2021/5/20 10:49
 * @description
 **/
public class DeleteListNode {
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
        }
    }

    public ListNode deleteNode(ListNode head, int val) {
        ListNode pre = head;
        ListNode current = head.next;
        if (head.val==val){
            return current;
        }
        while (pre!=null){
            if (current!=null&&current.val==val){
                pre.next=current.next;
                return head;
            }
            pre=current;
            current=current.next;
        }
        return head;
    }
}
