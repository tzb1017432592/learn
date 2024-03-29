package com.example.leetcode.hot;

/**
 *
 * 回文子串
 *
 * 给定一个字符串，你的任务是计算这个字符串中有多少个回文子串。
 *
 * 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串。
 * 
 * 示例 1：
 * 输入："abc"
 * 输出：3
 * 解释：三个回文子串: "a", "b", "c"
 *
 * 示例 2：
 * 输入："aaa"
 * 输出：6
 * 解释：6个回文子串: "a", "a", "a", "aa", "aa", "aaa"
 */
public class HuiWenChuang {
    public int countSubstrings(String s) {
        int len = s.length();
        int res=0;
        char[] chars = s.toCharArray();
        for (int i = 0; i < 2*len-1; i++) {
            int r=i/2,l=i/2+i%2;
            while (r>=0 && l<len && chars[r]==chars[l]){
                res++;
                r--;
                l++;
            }
        }
        return res;
    }
}
