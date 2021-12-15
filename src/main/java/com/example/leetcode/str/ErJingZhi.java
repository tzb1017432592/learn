package com.example.leetcode.str;

/**
 * @author tianzhoubing
 * @date 2021/11/23 17:20
 * @description
 *
 * 二进制求和
 *
 * 给你两个二进制字符串，返回它们的和（用二进制表示）。
 *
 * 输入为 非空 字符串且只包含数字 1 和 0。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: a = "11", b = "1"
 * 输出: "100"
 * 示例 2:
 *
 * 输入: a = "1010", b = "1011"
 * 输出: "10101"
 **/
public class ErJingZhi {
    public String addBinary(String a, String b) {
        int a2 = Integer.parseInt(a, 2);
        int b2 = Integer.parseInt(b, 2);
        return Integer.toBinaryString(a2 + b2);
    }
}
