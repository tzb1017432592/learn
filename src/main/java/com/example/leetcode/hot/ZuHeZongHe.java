package com.example.leetcode.hot;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author tianzhoubing
 * @date 2021/8/30 11:09
 * @description 组合总和
 * <p>
 * 给定一个无重复元素的正整数数组 candidates 和一个正整数 target ，找出 candidates 中所有可以使数字和为目标数 target 的唯一组合。
 * <p>
 * candidates 中的数字可以无限制重复被选取。如果至少一个所选数字数量不同，则两种组合是唯一的。 
 * <p>
 * 对于给定的输入，保证和为 target 的唯一组合数少于 150 个。
 * <p>
 * 示例 1：
 * 输入: candidates = [2,3,6,7], target = 7
 * 输出: [[7],[2,2,3]]
 * <p>
 * 示例 2：
 * 输入: candidates = [2,3,5], target = 8
 * 输出: [[2,2,2,2],[2,3,3],[3,5]]
 * <p>
 * 示例 3：
 * 输入: candidates = [2], target = 1
 * 输出: []
 * <p>
 * 示例 4：
 * 输入: candidates = [1], target = 1
 * 输出: [[1]]
 * <p>
 * 示例 5：
 * 输入: candidates = [1], target = 2
 * 输出: [[1,1]]
 **/
public class ZuHeZongHe {
    List<List<Integer>> res = new ArrayList<>(); //记录答案
    List<Integer> path = new ArrayList<>();  //记录路径

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        dfs(candidates, 0, target);
        return res;
    }

    public void dfs(int[] candidates, int index, int target) {
        if (target < 0) {
            return;
        }
        if (target == 0) {
            res.add(new ArrayList(path));
            return;
        }
        for (int i = index; i < candidates.length; i++) {
            if (candidates[i] <= target) {
                path.add(candidates[i]);
                dfs(candidates, i, target - candidates[i]); // 因为可以重复使用，所以还是i
                path.remove(path.size() - 1); //回溯，恢复现场
            }
        }
    }
}
