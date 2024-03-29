package com.example.leetcode.hot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
 *
 示例：
 给定 nums = [2, 7, 11, 15], target = 9
 因为 nums[0] + nums[1] = 2 + 7 = 9
 所以返回 [0, 1]
 */
public class TwoSum {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; ++i) {
            int c = target - nums[i];
            if (map.containsKey(c)) {
                return new int[]{map.get(c), i};
            }
            map.put(nums[i], i);
        }
        return new int[0];
    }
//所有的结果
    public static List<Map<Integer,Integer>> allTwoSum(int[] nums, int target){
        Map<Integer,Integer> tempMap=new HashMap<>();
        List<Map<Integer,Integer>> results=new ArrayList<>();
        for (int i=0;i<nums.length;i++){
            int c = target - nums[i];
            if (tempMap.containsKey(c)){
                Map<Integer,Integer> resultMap=new HashMap<>();
                resultMap.put(tempMap.get(c),i);
                results.add(resultMap);
            }
            tempMap.put(nums[i],i);
        }
        return results;
    }

    public static void main(String[] args) {
       int[] nums ={0,2, 7, 3,6,11,15,9};
        System.out.println(allTwoSum(nums,9));
    }
}
