package com.example.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 汇总区间
 * 输入：nums = [0,1,2,4,5,7]
 * 输出：["0->2","4->5","7"]
 * 解释：区间范围是：
 * [0,2] --> "0->2"
 * [4,5] --> "4->5"
 * [7,7] --> "7"
 */
public class HuiZongQuJian {
    public static List<String> qujianhuizong(int [] nums){
        List<String> result = new ArrayList<>();
        if (null == nums || nums.length ==0) {
            return result;
        } else if (nums.length == 1) {
            result.add(String.valueOf(nums[0]));
            return result;
        }
        int index = 1;
        int begin = nums[0];
        for (int i=1;i<nums.length;i++) {
            if (nums[i] - nums[i-1] == 1) {
                index+=i+1;//做标记，index!=i代表程序进入了这个代码块，index==i代表程序未进入这个代码块
                if (i>=nums.length-1){
                    result.add(index!=i?begin+ "->" + nums[i]:begin+"");
                }
                continue;
            }
            result.add(index!=i?begin+ "->" + nums[i - 1]:begin+"");
            begin=nums[i];
            index=i+1;
            if (i>=nums.length-1){
                result.add(begin+"");
            }

        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums={0,1,2,4,5,7,9,10};
        System.out.println(qujianhuizong(nums));
    }
}
