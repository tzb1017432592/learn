package com.example.leetcode;

/**
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 *
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 */
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
