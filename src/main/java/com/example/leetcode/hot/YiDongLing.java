package com.example.leetcode.hot;

/**
 * 移动零
 *
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 *
 * 示例:
 *
 * 输入: [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 *
 * 双指针
 *
 */
public class YiDongLing {
    public void moveZeroes(int[] nums) {
        int fast=0,slow=0;
        while (fast<nums.length){
            if (nums[fast]!=0){
                int temp = nums[slow];
                nums[slow]=nums[fast];
                nums[fast] = temp;
                slow++;
            }
            fast++;
        }
    }
}
