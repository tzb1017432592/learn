package com.example.leetcode.hot;

import java.util.*;

/**
 * 三数相加
 *
 * 给你一个包含 n 个整数的数组nums，判断nums中是否存在三个元素 a，b，c ，
 * 使得a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
 *
 *输入：nums = [-1,0,1,2,-1,-4]
 * 输出：[[-1,-1,2],[-1,0,1]]
 */
public class SanShuXiangJia {
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> results=new ArrayList<>();
        if (nums.length<=2){
            return results;
        }
        Arrays.sort(nums);
        for (int i=0;i<nums.length;i++){
            if (i>0&&nums[i]==nums[i-1]){
                continue;
            }
            if (nums[i]>0){
                break;
            }
            int target=nums[i];
            int right=nums.length-1;
            int left=i+1;
            while (left<right){
                if ((target+nums[left]+nums[right])==0){
                    List<Integer> temps=new ArrayList<>();
                    temps.add(target);
                    temps.add(nums[left]);
                    temps.add(nums[right]);
                    results.add(temps);
                    left++;
                    right--;
                    while (left < right && nums[left] == nums[left - 1]) {
                        left++;
                    }
                    while (left < right && nums[right] == nums[right + 1]){
                        right--;
                    }
                }else if ((target+nums[left]+nums[right])<0){
                    left++;
                }else if ((target+nums[left]+nums[right])>0){
                    right--;
                }
            }
        }
        return results;
    }

    public static void main(String[] args) {
        int[] nums = {-1,0,1,2,-1,-4};
        System.out.println(threeSum(nums));
    }
}
