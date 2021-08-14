package com.example.leetcode.hot;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tianzhoubing
 * @date 2021/8/14 10:51
 * @description
 *
 * 子集
 *
 * 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
 *
 * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,3]
 * 输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 * 示例 2：
 *
 * 输入：nums = [0]
 * 输出：[[],[0]]
 **/
public class ZiJi {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        loop(0,nums,list,temp);
        return list;
    }

    private void loop(int i, int[] nums, List<List<Integer>> list, List<Integer> temp) {
        list.add(new ArrayList<>(temp));
        for (int j = i; j < nums.length; j++) {
            temp.add(nums[j]);
            loop(j+1,nums, list, temp);
            temp.remove(temp.size() - 1);
        }
    }

}
