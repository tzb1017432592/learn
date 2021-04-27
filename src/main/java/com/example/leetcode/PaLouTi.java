package com.example.leetcode;

public class PaLouTi {
    public int climbStairs(int n) {
        int r=1,a=0,b=0;
       for (int i=0;i<n;i++){
           a=b;//保存上上层结果，也就是保存n-2层的结果
           b=r;//保存上层结果，也就是保存n-1层结果
           r=a+b;
       }
       return r;
    }
}
