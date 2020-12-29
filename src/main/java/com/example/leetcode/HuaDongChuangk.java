package com.example.leetcode;

import javax.validation.constraints.Max;
import java.util.HashSet;
import java.util.Set;

/**
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 * <p>
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 */
public class HuaDongChuangk {
    public static int lengthOfLongestSubstring(String s) {
        Set hashSet = new HashSet<String>();
        int index = 0, len = 0, length = s.length();
        for (int i = 0; i < length; i++) {
            if (hashSet.size() != 0) {
                hashSet.remove(s.charAt(i - 1));
            }
            while (index < length && !hashSet.contains(s.charAt(index))) {
                hashSet.add(s.charAt(index));
                index++;
            }
            len = Math.max(len, index - i);
        }
        return len;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("abcabcbb"));
    }
}
