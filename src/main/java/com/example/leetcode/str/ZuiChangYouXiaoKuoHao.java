package com.example.leetcode.str;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author tianzhoubing
 * @date 2021/11/22 22:27
 * @description
 *
 * 最长有效括号
 *
 * 给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的长度。
 *
 * 示例 1：
 *
 * 输入：s = "(()"
 * 输出：2
 * 解释：最长有效括号子串是 "()"
 * 示例 2：
 *
 * 输入：s = ")()())"
 * 输出：4
 * 解释：最长有效括号子串是 "()()"
 * 示例 3：
 *
 * 输入：s = ""
 **/
public class ZuiChangYouXiaoKuoHao {
    public int longestValidParentheses(String s) {
        Deque<Integer> deque = new LinkedList<>();
        int length = s.length();
        int maxLen = 0;
        for (int i = 0; i < length; i++) {
            int n = -1;
            if (s.charAt(i)=='('){
                n = 1;
            }
            if (deque.size()>0 && deque.peek()+n==0) {
                while (deque.peek()+n==0){
                    deque.pop();
                }
            }else {
                deque.push(n);
            }
        }
    }
}
