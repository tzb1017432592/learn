package com.example.leetcode.hot;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * @author tianzhoubing
 * @date 2021/8/23 11:14
 * @description
 * 用栈实现队列
 *
 * 双栈
 **/
public class MyQueue {
    Deque<Integer> out ;
    Deque<Integer> in ;

    public MyQueue() {
        out = new LinkedList<>();
        in = new LinkedList<>();
    }

    /** Push element x to the back of queue. */
    public void push(int x) {
        in.push(x);
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        if (out.isEmpty()){
            while (in.peek() != null){
                out.push(in.pop());
            }
        }
        return out.pop();
    }

    /** Get the front element. */
    public int peek() {
        if (out.isEmpty()){
            while (in.peek() != null){
                out.push(in.pop());
            }
        }
        return out.peek();
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        return out.isEmpty()&&in.isEmpty();
    }
}
