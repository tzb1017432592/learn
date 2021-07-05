package com.example.leetcode.jianzhioffer;

/**
 * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。输入一个递增排序的数组的一个旋转，
 * 输出旋转数组的最小元素。例如，数组[3,4,5,1,2] 为 [1,2,3,4,5] 的一个旋转，该数组的最小值为1。
 * <p>
 * 输入：[3,4,5,1,2]
 * 输出：1
 * <p>
 * 输入：[2,2,2,0,1]
 * 输出：0
 */
public class XuanZhuangShuZu {
    public int minArray(int[] numbers) {
        int r = numbers.length - 1;
        int l = 0;
        while (r > l) {
            int m = (r + l) / 2;
            if (numbers[m] > numbers[r]) {
                l = m + 1;
            } else if (numbers[m] < numbers[r]) {
                r = m;
            } else {
                r--;
            }
        }
        return numbers[l];
    }
}
