package com.example.leetcode.hot;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author tianzhoubing
 * @date 2021/8/13 18:07
 * @description 最长连续序列
 *
 * 给定一个未排序的整数数组 nums ，
 * 找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
 *
 * 请你设计并实现时间复杂度为O(n) 的算法解决此问题。
 * 示例 1：
 * 输入：nums = [100,4,200,1,3,2]
 * 输出：4
 * 解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。
 *
 * 示例 2：
 * 输入：nums = [0,3,7,2,5,8,4,6,0,1]
 * 输出：9
 **/
public class ZuiChangLianXuXuLie {
    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return 1;
        }
        Arrays.sort(nums);
        int max = 1;
        int temp = 1;
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i - 1] + 1 == nums[i]) {
                temp++;
                max = Math.max(max, temp);
            }
            if (i > 0 && nums[i - 1] == nums[i]) {
                continue;
            }
            if (i > 0 && nums[i - 1] + 1 != nums[i]) {
                temp = 1;
            }
        }
        return max;
    }

    public int longestConsecutive2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return 1;
        }
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int max = 1;
        for (Integer num : set) {
            if (!set.contains(num-1)) {
                int t = num;
                int temp = 1;
                while (set.contains(t + 1)) {
                    t++;
                    temp++;
                    max = Math.max(max, temp);
                }
            }
        }
        return max;
    }
}
