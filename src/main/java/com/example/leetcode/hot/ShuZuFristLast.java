package com.example.leetcode.hot;

/**
 * @author tianzhoubing
 * @date 2021/8/17 17:29
 * @description
 *
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 *
 * 如果数组中不存在目标值 target，返回[-1, -1]。
 *
 * 进阶：
 *
 * 你可以设计并实现时间复杂度为O(log n)的算法解决此问题吗？
 *
 *
 * 示例 1：
 * 输入：nums = [5,7,7,8,8,10], target = 8
 * 输出：[3,4]
 *
 * 示例2：
 * 输入：nums = [5,7,7,8,8,10], target = 6
 * 输出：[-1,-1]
 *
 * 示例 3：
 * 输入：nums = [], target = 0
 * 输出：[-1,-1]
 *
 *
 * 二分法
 **/
public class ShuZuFristLast {
    public int[] searchRange(int[] nums, int target) {
        return binary(nums, target, 0, nums.length-1);
    }

    private int[] binary(int[] nums, int target, int start, int end) {
        if (start>end){
            return new int[] {-1, -1};
        }
        int mid = (start+end) / 2;
        if (nums[mid] > target) {
            return binary(nums, target, start, mid-1);
        }
        if (nums[mid] < target) {
            return binary(nums, target, mid+1, end);
        }
        if (nums[mid] == target) {
            int first = mid;
            int last = mid;
            while (first - 1 >= 0 && nums[first - 1] == target) {
                first--;
            }
            while (last + 1 < nums.length && nums[last + 1] == target) {
                last++;
            }
            return new int[]{first, last};
        }
        return new int[] {-1, -1};
    }
}
