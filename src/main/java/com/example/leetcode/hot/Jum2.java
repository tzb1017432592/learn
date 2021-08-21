package com.example.leetcode.hot;

/**
 * 跳跃游戏2
 */
public class Jum2 {
    public int jump(int[] nums) {
        int step=0;
        int maxJump=0;
        int end=0;
        for (int i = 0; i < nums.length-1; i++) {
            maxJump=Math.max(maxJump,nums[i]+i);
            if(i == end){ //遇到边界，就更新边界，并且步数加一
                end = maxJump;
                step++;
            }
        }
        return step;
    }
}
