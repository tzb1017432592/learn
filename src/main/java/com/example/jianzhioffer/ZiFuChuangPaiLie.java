package com.example.jianzhioffer;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 回溯
 *
 * 输入一个字符串，打印出该字符串中字符的所有排列。
 * 你可以以任意顺序返回这个字符串数组，但里面不能有重复元素。
 *
 * 输入：s = "abc"
 * 输出：["abc","acb","bac","bca","cab","cba"]
 */
public class ZiFuChuangPaiLie {
    public String[] permutation(String s) {
        Set<String> res = new HashSet<>();
        backtrack(s.toCharArray(), "", new boolean[s.length()], res);
        return res.toArray(new String[res.size()]);
    }

    private void backtrack(char[] chars, String temp, boolean[] visited, Set<String> res) {
        //边界条件判断，当选择的字符长度等于原字符串长度的时候，说明原字符串的字符都已经
        //选完了
        if (temp.length() == chars.length) {
            res.add(temp);
            return;
        }
        //每一个节点我们都要从头开始选
        for (int i = 0; i < chars.length; i++) {
            //已经选择过的就不能再选了
            if (visited[i])
                continue;
            //表示选择当前字符
            visited[i] = true;
            //把当前字符选择后，到树的下一层继续选
            backtrack(chars, temp + chars[i], visited, res);
            //递归往回走的时候要撤销选择
            visited[i] = false;
        }
    }

    public static void main(String[] args) {
        List<Integer> list = new LinkedList<>();

        list.add(3);
        list.add(2);
        list.add(5);

        list = list.stream().sorted().collect(Collectors.toList());

        System.out.println(list);
    }
}
