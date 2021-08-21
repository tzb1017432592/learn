package com.example.leetcode.hot;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 用队列实现栈
 */
public class MyStack {
    Queue<Integer> queue1;
    Queue<Integer> queue2;

    public MyStack() {
        queue1 = new LinkedList<>();
        queue2 = new LinkedList<>();
    }


    public void push(int x) {
        queue2.offer(x);
        while (queue1.size()!=0){
            queue2.offer(queue1.poll());
        }
        Queue<Integer> temp;
        temp = queue1;
        queue1 = queue2;
        queue2 = temp;
    }


    public int pop() {
        return queue1.poll();
    }


    public int top() {
        return queue1.peek();
    }


    public boolean empty() {
        return queue1.size()<=0;
    }
}
