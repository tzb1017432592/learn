package com.example.leetcode.hot;

/**
 * 跳跃游戏1
 */
public class Jum {
    public boolean canJump(int[] nums) {
        int maxJump=0;
        for (int i = 0; i < nums.length-1; i++) {
            if (i<=maxJump) {
                maxJump = Math.max(maxJump, nums[i]+i);
                if (maxJump>=nums.length-1){
                    return true;
                }
            }else {
                return false;
            }
        }
        return false;
    }
}
