package com.example.leetcode.hot;

import java.util.Arrays;

/**
 * 多数元素
 *
 * 给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数 大于  n/2  的元素。
 *
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 */
public class DuoShuChongFuShu {
    public int majorityElement(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }

    public static int majorityElement2(int[] nums) {
        int half=nums.length/2;
        int result=0;
        for (int i = 0; i < nums.length; i++) {
            int tempi = i-1;
            if (i>0&&nums[tempi]==nums[i]){
                continue;
            }
            int temp=nums[i];
            int tempCount=0;
            for (int num : nums) {
                if (temp == num) {
                    tempCount++;
                }
                if (tempCount > half) {
                    return num;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int i = majorityElement2(new int[]{6, 5, 5});
        System.out.println(i);
    }
}
