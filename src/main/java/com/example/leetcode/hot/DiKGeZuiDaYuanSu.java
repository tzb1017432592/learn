package com.example.leetcode.hot;

/**
 * @author tianzhoubing
 * @date 2021/8/23 10:36
 * @description
 *
 * 数组中的第K个最大元素
 **/
public class DiKGeZuiDaYuanSu {
    public int findKthLargest(int[] nums, int k) {
        sort(nums,k);
        return nums[nums.length-k];
    }
    private void sort(int[] array,int k) {
        // array.length - 1是因为最后一轮不需要排序
        for (int i = 0; i < k; i++) {
            // array.length - i是因为每一轮都能确定排序好一个数
            for (int j = 0; j < array.length - 1 - i; j++) {
                int temp;
                if (array[j] > array[j + 1]) {
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }
}
