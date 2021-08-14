package com.example.leetcode.hot;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tianzhoubing
 * @date 2021/8/14 14:24
 * @description
 *
 * 全排列
 *
 * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,3]
 * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 * 示例 2：
 *
 * 输入：nums = [0,1]
 * 输出：[[0,1],[1,0]]
 * 示例 3：
 *
 * 输入：nums = [1]
 * 输出：[[1]]
 *
 **/
public class QuanPaiLie {
    public List<List<Integer>> permute(int[] nums) {
        boolean[] bs = new boolean[nums.length];
        List<List<Integer>> result = new ArrayList<>();
        loop(bs,nums,result,new ArrayList<>());
        return result;
    }

    private void loop(boolean[] bs, int[] nums,List<List<Integer>> result,List<Integer> mid) {
        if (mid.size()==nums.length){
            List<Integer> temp = new ArrayList<>(mid);
            result.add(temp);
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (bs[i]){
                continue;
            }
            mid.add(nums[i]);
            bs[i]=true;
            loop(bs,nums,result,mid);
            mid.remove(Integer.valueOf(nums[i]));
            bs[i]=false;
        }
    }
}
