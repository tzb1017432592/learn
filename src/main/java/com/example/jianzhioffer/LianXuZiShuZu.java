package com.example.jianzhioffer;

/**
 * @author tianzhoubing
 * @date 2021/5/20 17:36
 * @description
 * 输入一个整型数组，数组中的一个或连续多个整数组成一个子数组。求所有子数组的和的最大值。
 * 要求时间复杂度为O(n)。
 * <p>
 * 输入: nums = [-2,1,-3,4,-1,2,1,-5,4]
 * 输出: 6
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 **/
public class LianXuZiShuZu {
    public int maxSubArray(int[] nums) {
        int maxSum = nums[0];
        int target;
        for (int i = 0; i < nums.length; i++) {
            target = nums[i];
            maxSum = Math.max(maxSum, target);
            if (target>0) {
                for (int j = i + 1; j < nums.length; j++) {
                    target += nums[j];
                    maxSum = Math.max(maxSum, target);
                }
            }

        }
        return maxSum;
    }
}
