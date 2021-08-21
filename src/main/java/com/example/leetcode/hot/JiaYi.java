package com.example.leetcode.hot;

import java.util.ArrayList;
import java.util.List;

/**
 * 加一
 *
 * 给定一个由 整数 组成的 非空 数组所表示的非负整数，在该数的基础上加一。
 * 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
 * 你可以假设除了整数 0 之外，这个整数不会以零开头。
 *
 * 示例 1：
 * 输入：digits = [1,2,3]
 * 输出：[1,2,4]
 * 解释：输入数组表示数字 123。
 *
 * 示例 2：
 * 输入：digits = [4,3,2,1]
 * 输出：[4,3,2,2]
 * 解释：输入数组表示数字 4321。
 *
 *
 * 示例 3：
 * 输入：digits = [0]
 * 输出：[1]
 *
 */
public class JiaYi {
    public int[] plusOne(int[] digits) {
        int plus=0;
        List<Integer> res = new ArrayList<>();
        for (int i = digits.length-1; i >= 0; i--) {
            int temp;
            if (i==digits.length-1) {
                temp = digits[i] + 1;
            }else {
                temp = digits[i] + plus;
            }
            if (temp>=10){
                if (i==0){
                    res.add(temp%10);
                    res.add(temp/10);
                }else {
                    plus=1;
                    res.add(temp%10);
                }
            }else {
                plus=0;
                res.add(temp);
            }
        }
        int[] newint = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            newint[newint.length-1-i]=res.get(i);
        }
        return newint;
    }

    public static int[] plusOne2(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
            digits[i]++;
            digits[i] = digits[i] % 10;
            if (digits[i] != 0) {
                return digits;
            }
        }
        digits = new int[digits.length + 1];
        digits[0] = 1;
        return digits;
    }

    public static void main(String[] args) {
        JiaYi.plusOne2(new int[]{4,5,7,8,8});
    }

}
