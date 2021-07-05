package com.example.leetcode.jianzhioffer;

/**
 * @author tianzhoubing
 * @date 2021/5/20 13:55
 * @description
 *
 * 获取倒数第k个节点
 **/
public class DaoShuJieDian {
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
        }
    }
    public ListNode getKthFromEnd(ListNode head, int k) {
        ListNode current = head;
        ListNode res = head;
        int size=0;
        if (head==null){
            return null;
        }
        while (current!=null){
            current=current.next;
            size++;
        }
        if (size>k){
            for (int i = 0; i < size - k + 1; i++) {
                res=res.next;
            }
        }
        return res;
    }
}
