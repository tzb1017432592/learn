package com.example.leetcode.jianzhioffer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author tianzhoubing
 * @date 2021/5/20 9:44
 * @description
 *
 *
 *
 * 在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。数组中某些数字是重复的，但不知道有几个数字重复了，
 * 也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。
 * 输入：
 * [2, 3, 1, 0, 2, 5, 3]
 * 输出：2 或 3
 **/


public class ChongFuSuZu {
    public List<Integer> findRepeatNumbers(int[] nums) {
        Set<Integer> set=new HashSet<>();
        List<Integer> results=new ArrayList<>();
        for (int num : nums) {
            if (!set.contains(num)){
                set.add(num);
            }else {
                results.add(num);
            }
        }
        return results;
    }

    public int findRepeatNumber(int[] nums) {
        Set<Integer> set=new HashSet<>();
        for (int num : nums) {
            if (!set.contains(num)){
                set.add(num);
            }else {
                return num;
            }
        }
        return -1;
    }
}
