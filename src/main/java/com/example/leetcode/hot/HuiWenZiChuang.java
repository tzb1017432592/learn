package com.example.leetcode.hot;

/**
 * 回文子串
 */
public class HuiWenZiChuang {
    public String longestPalindrome(String s) {
        int len = s.length();
        if (len<2){
            return s;
        }
        char[] chars = s.toCharArray();
        boolean[][] dp = new boolean[len][len];
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }
        int begin=0;
        int maxLen=1;
        int end=1;
        for (int L = 2; L <= len; L++) {
            for (int left = 0; left < len; left++) {
                //right-left+1=L;
                int right = left+L-1;
                if (right>=len){
                    break;
                }
                if (chars[left]!=chars[right]){
                    dp[left][right] = false;
                }else {
                    //right-1-(left+1)+1<2的话，代表这个子串的长度不超过3，
                    // 长度不超过3的子串只有收尾相等必然是回文子串，
                    // 例如:a,aa,aba都是长度不超3的，首尾相等就可以直接判断是回文了
                    if (right-left < 3){
                        dp[left][right] = true;
                    }else {
                        //动态规划，子串是否是回文，要判断除去首尾后内部的串是否是回文
                        dp[left][right] = dp[left+1][right-1];
                    }
                }
                if (dp[left][right] && right - left + 1 > maxLen){
                    maxLen = right - left + 1;
                    begin = left;
                    end = right+1;
                }
            }
        }
        return s.substring(begin,end);
    }

    public static void main(String[] args) {
        System.out.println(0%2);
    }
}
