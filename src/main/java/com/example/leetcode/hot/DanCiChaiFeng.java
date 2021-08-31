package com.example.leetcode.hot;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 */
public class DanCiChaiFeng {
    public boolean wordBreak(String s, List<String> wordDict) {
        return dfs(s, wordDict, 0);
    }

    //start表示的是从字符串s的哪个位置开始
    public boolean dfs(String s, List<String> wordDict, int start) {
        //字符串中的所有字符都遍历完了，也就是到叶子节点了，说明字符串s可以拆分成
        //在字典中出现的单词，直接返回true
        if (start == s.length())
            return true;
        //开始拆分字符串s，
        for (int i = start + 1; i <= s.length(); i++) {
            //如果截取的子串不在字典中，继续截取更大的子串
            if (!wordDict.contains(s.substring(start, i)))
                continue;
            //如果截取的子串在字典中，继续剩下的拆分，如果剩下的可以拆分成
            //在字典中出现的单词，直接返回true，如果不能则继续
            //截取更大的子串判断
            if (dfs(s, wordDict, i))
                return true;
        }
        return false;
    }

}
