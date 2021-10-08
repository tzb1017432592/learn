package com.example.leetcode.hot;

/**
 * @author tianzhoubing
 * @date 2021/8/23 10:05
 * @description
 *
 * 打家劫舍2
 **/
public class DaJiaJieSe2 {
    public int rob(int[] nums) {
        int length = nums.length;
        if (length == 1) {
            return nums[0];
        } else if (length == 2) {
            return Math.max(nums[0], nums[1]);
        }
        return Math.max(robRange(nums, 0, length - 2), robRange(nums, 1, length - 1));
    }

    public int robRange(int[] nums, int start, int end) {
        int first = nums[start], second = Math.max(nums[start], nums[start + 1]);
        for (int i = start + 2; i <= end; i++) {
            int temp = second;
            second = Math.max(first + nums[i], second);
            first = temp;
        }
        return second;
    }

    public int rob2(int[] nums) {
        int length = nums.length;
        int[] dp1 = new int[length];//偷头一家不偷最后一家
        int[] dp2 = new int[length];//偷最后一家，不偷头一家
        if (length == 1) {
            return nums[0];
        } else if (length == 2) {
            return Math.max(nums[0], nums[1]);
        }
        dp1[1] = dp1[0] = nums[0];
        dp2[0] = 0;
        dp2[1] = nums[1];
        for (int i = 2; i < length; i++) {
            dp1[i] = Math.max(dp1[i-2]+nums[i], dp1[i-1]);
            dp2[i] = Math.max(dp2[i-2]+nums[i], dp2[i-1]);
        }
        return Math.max(dp1[length - 2], dp2[length - 1]);
    }
}
