package com.example.leetcode.jianzhioffer;

/**
 * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有奇数位于数组的前半部分，所有偶数位于数组的后半部分。
 *
 * 输入：nums = [1,2,3,4]
 * 输出：[1,3,2,4]
 * 注：[3,1,2,4] 也是正确的答案之一。
 */
public class ShuZuXunXu {
    public int[] exchange(int[] nums) {
        int r=nums.length-1;
        int l=0;
        while (r>l){
            while ((nums[l]&1)!=0&&l<nums.length-1){
                l++;
            }while ((nums[r]&1)!=1&&r>0){
                r--;
            }
            if (r>l){
                int t=nums[r];
                nums[r]=nums[l];
                nums[l]=t;
            }
        }
        return nums;
    }
}
