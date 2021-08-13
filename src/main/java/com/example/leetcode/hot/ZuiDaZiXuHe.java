package com.example.leetcode.hot;

/**
 * @author tianzhoubing
 * @date 2021/8/13 16:39
 * @description 最大子序和
 *
 * 示例 1：
 * 输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
 * 输出：6
 * 解释：连续子数组[4,-1,2,1] 的和最大，为6 。
 *
 * 示例 2：
 * 输入：nums = [1]
 * 输出：1
 *
 * 示例 3：
 * 输入：nums = [0]
 * 输出：0
 *
 * 示例 4：
 * 输入：nums = [-1]
 * 输出：-1
 *
 * 示例 5：
 * 输入：nums = [-100000]
 * 输出：-100000
 **/
public class ZuiDaZiXuHe {
    public int maxSubArray(int[] nums) {
        int max = -Integer.MAX_VALUE;
        if (nums.length == 1) {
            return nums[0];
        }
        for (int i = 0; i < nums.length; i++) {
            int temp = nums[i];
            max = Math.max(temp, max);
            for (int j = i + 1; j < nums.length; j++) {
                temp += nums[j];
                max = Math.max(temp, max);
            }
        }
        return max;
    }

    public int maxSubArray2(int[] nums) {
        int pre = 0, maxAns = nums[0];
        for (int x : nums) {
            pre = Math.max(pre + x, x);
            maxAns = Math.max(maxAns, pre);
        }
        return maxAns;
    }

}
