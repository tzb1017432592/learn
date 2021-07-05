package com.example.leetcode.jianzhioffer;

import java.util.HashSet;
import java.util.Set;

/**
 * 请从字符串中找出一个最长的不包含重复字符的子字符串，计算该最长子字符串的长度。
 *
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 */
public class ZuiChangZiZhuang {
    public int lengthOfLongestSubstring(String s) {
        char[] chars = s.toCharArray();
        Set<Character> characterSet=new HashSet<>();
        int max=0;
        int index=0;
        int len=0;
        for (int i=0;i<chars.length;i++){
            if (characterSet.size()>0){
                characterSet.remove(chars[i-1]);
                len--;
            }
            while (index<chars.length&&!characterSet.contains(chars[index])){
               characterSet.add(chars[index]);
               index++;
               len++;
               max=Math.max(max,len);
            }
        }
        return max;
    }

}
