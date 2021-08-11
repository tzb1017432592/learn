package com.example.springLearn;

/**
 * @author tianzhoubing
 * @date 2021/5/24 19:05
 * @description
 **/
public class Test {
    class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }
    ListNode head = null;
    ListNode tail = null;
    public void mk() {
        for (int i = 1; i < 13; i++) {
            if (head == null) {
                head = new ListNode(i);
                tail = head;
            } else {
                ListNode node = new ListNode(i);
                tail.next = node;
                tail = node;
            }
        }
        tail.next = head;
    }

    private void loop() {
        ListNode temp = head;
        while (true) {
            for (int i = 1; i < 5; i++) {
                temp = temp.next;
                if (i==4){
                    if (temp.next!=null){
                        temp.next=temp.next.next;
                    }
                }
                if (temp.next == null) {
                    break;
                }
            }
        }
    }

}
