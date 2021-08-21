package com.example.leetcode.hot;

/**
 * 搜索插入位置
 *
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 *
 * 请必须使用时间复杂度为 O(log n) 的算法。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: nums = [1,3,5,6], target = 5
 * 输出: 2
 * 示例 2:
 *
 * 输入: nums = [1,3,5,6], target = 2
 * 输出: 1
 * 示例 3:
 *
 * 输入: nums = [1,3,5,6], target = 7
 * 输出: 4
 * 示例 4:
 *
 * 输入: nums = [1,3,5,6], target = 0
 * 输出: 0
 * 示例 5:
 *
 * 输入: nums = [1], target = 0
 * 输出: 0
 *
 */
public class SouSuoChaRuWeiZhi {
    public static int searchInsert(int[] nums, int target) {
        return binarySearch(nums,target,0,nums.length-1);
    }

    private static int binarySearch(int[] nums, int target, int start, int end) {
        if (target < nums[start]) {
            return start;
        }
        if (target > nums[end]){
            return end+1;
        }
        int mid = (start+end)/2;
        if (nums[mid]==target){
            return mid;
        } else if (target>nums[mid]){
            return binarySearch(nums, target, mid+1, end);
        }else if (target<nums[mid]){
            return binarySearch(nums, target, start, mid-1);
        }
        return mid;
    }

    public static void main(String[] args) {
        searchInsert(new int[]{1,3},2);
    }
}
