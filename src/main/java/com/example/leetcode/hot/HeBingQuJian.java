package com.example.leetcode.hot;

import com.example.dataStructure.array.Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author tianzhoubing
 * @date 2021/8/23 11:38
 * @description
 *
 * 合并区间
 **/
public class HeBingQuJian {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        List<int[]> result = new ArrayList<>();
        for (int[] interval : intervals) {
            int l = interval[0], r = interval[1];
            if (result.size() == 0) {
                result.add(new int[]{l, r});
            } else {
                int[] ints = result.get(result.size() - 1);
                if (ints[0] == l && ints[1] < r) {
                    ints[1] = r;
                } else if (ints[0] < l && ints[1] >= l && ints[1] <= r) {
                    ints[1] = r;
                } else if (ints[0] < l && ints[1] < l) {
                    result.add(new int[]{l, r});
                }
            }
        }
        return result.toArray(new int[result.size()][]);
    }

}
