package com.example.jianzhioffer;

/**
 * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 *
 * 进阶：你能尝试使用一趟扫描实现吗？
 *
 *
 * 输入：head = [1,2,3,4,5], n = 2
 * 输出：[1,2,3,5]
 */
public class removeLinked {
    class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head==null||head.next==null){
            return null;
        }
        ListNode dumy=new ListNode(0,head);
        ListNode start=dumy,tail=dumy,temp=null;
        int tailIndex=0;

        while (tail.next!=null){
            tail=tail.next;
            tailIndex++;
            if (tailIndex>=n){
                temp=start;
                start=start.next;
            }
        }
        if (tailIndex<n){
            return null;
        }
        temp.next=start.next;
        return dumy.next;
    }
}
