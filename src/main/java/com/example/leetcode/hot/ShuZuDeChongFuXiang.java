package com.example.leetcode.hot;

/**
 * 删除有序数组中的重复项
 *
 * 给你一个有序数组 nums ，请你 原地 删除重复出现的元素，使每个元素 只出现一次 ，返回删除后数组的新长度。
 * 不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
 *
 * 双指针
 *
 */
public class ShuZuDeChongFuXiang {
    public static int removeDuplicates(int[] nums) {
        if (nums.length==0){
            return 0;
        }
        int slow=1;
        for (int i = 0; i < nums.length; i++) {
            if (i>0&&nums[i]!=nums[i-1]){
                nums[slow]=nums[i];
                slow++;
            }
        }
        return slow;
    }
    public int removeDuplicates2(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        int fast = 1, slow = 1;
        while (fast < n) {
            if (nums[fast] != nums[fast - 1]) {
                nums[slow] = nums[fast];
                ++slow;
            }
            ++fast;
        }
        return slow;
    }

    public static void main(String[] args) {
        removeDuplicates(new int[]{0,0,1,1,1,2,2,3,3,4});
    }
}
