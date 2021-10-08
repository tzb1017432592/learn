package com.example.leetcode.hot;

/**
 * @author tianzhoubing
 * @date 2021/9/8 3:20
 * @description
 *
 * 和为 K 的子数组
 *
 *
 * 给你一个整数数组 nums 和一个整数 k ，请你统计并返回该数组中和为 k 的连续子数组的个数。
 *
 *
 *
 * 示例 1：
 * 输入：nums = [1,1,1], k = 2
 * 输出：2
 *
 * 示例 2：
 * 输入：nums = [1,2,3], k = 3
 * 输出：2
 *
 **/
public class HeWeiKDeZiShuZhu {
    public int subarraySum(int[] nums, int k) {
        int count = 0;
        for (int start = 0; start < nums.length; ++start) {
            int sum = 0;
            for (int end = start; end >= 0; --end) {
                sum += nums[end];
                if (sum > k) {
                    break;
                }
                if (sum == k) {
                    count++;
                }
            }
        }
        return count;
    }
}
