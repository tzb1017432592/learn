package com.example.leetcode.hot;

/**
 * 两两交换链表中的节点
 * <p>
 * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 * <p>
 * 输入：head = [1,2,3,4]
 * 输出：[2,1,4,3]
 * <p>
 * 示例 2：
 * 输入：head = []
 * 输出：[]
 * <p>
 * 示例 3：
 * 输入：head = [1]
 * 输出：[1]
 *
 * 新链表连接
 */
public class JiaoHuangLinked {
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

    public ListNode swapPairs(ListNode head) {
        ListNode newlist = new ListNode();
        ListNode temp = newlist;
        while (head!=null&&head.next!=null){
            temp.next=head.next;
            ListNode headNext=head.next.next;
            head.next=null;
            temp.next.next=head;
            head=headNext;
            temp=temp.next.next;
        }
        if (head!=null){
            temp.next=head;
        }
        return newlist.next;
    }

    public void main(String[] args) {
        //ListNode listNode = new ListNode(1,new ListNode(2,new ListNode(3,new ListNode(4))));
       // System.out.println(JiaoHuangLinked.swapPairs(listNode));
    }
}
