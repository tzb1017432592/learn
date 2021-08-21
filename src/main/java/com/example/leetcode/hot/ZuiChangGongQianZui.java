package com.example.leetcode.hot;

/**
 * 最长公共前缀
 *
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 *
 * 如果不存在公共前缀，返回空字符串 ""。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：strs = ["flower","flow","flight"]
 * 输出："fl"
 * 示例 2：
 *
 * 输入：strs = ["dog","racecar","car"]
 * 输出：""
 * 解释：输入不存在公共前缀。
 *
 */
public class ZuiChangGongQianZui {
    public String longestCommonPrefix(String[] strs) {
        if (strs==null||strs.length==0){
            return null;
        }
        int len = strs[0].length();
        boolean flag=true;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            if (!flag){
                break;
            }
            String str="";
            for (String s : strs) {
                if (i==s.length()){
                    flag=false;
                }
                if (flag) {
                    if (str.length() == 0) {
                        str = s.charAt(i) + "";
                    }
                    flag = str.equals(s.charAt(i) + "");
                }
            }
            if (flag){
                sb.append(str);
            }
        }
        return sb.toString();
    }
}
