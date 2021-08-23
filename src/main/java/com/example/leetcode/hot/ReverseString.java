package com.example.leetcode.hot;

/**
 * @author tianzhoubing
 * @date 2021/8/23 11:22
 * @description
 **/
public class ReverseString {
    public void reverseString(char[] s) {
        int start = 0;
        int end = s.length-1;
        while (start < end) {
            char temp = s[start];
            s[start] = s[end];
            s[end] = temp;
            start++;
            end--;
        }
    }
}
