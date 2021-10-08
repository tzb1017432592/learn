package com.example.leetcode.hot;

import java.util.ArrayList;
import java.util.List;

/**
 * 括号生成
 */
public class ShengChengKuoHao {
    List<String> res = new ArrayList<>();
    public List<String> generateParenthesis(int n) {
        if(n <= 0){
            return res;
        }
        generateParenthesis("",n,n);
        return res;
    }

    private void generateParenthesis(String s, int left, int right) {
        if (left==0&&right==0){
            res.add(s);
            return;
        }
        if (left==right){
            generateParenthesis(s+"(",left-1,right);
        } else if(left < right){
            if (left>0){
                generateParenthesis(s+"(", left-1, right);
            }
            generateParenthesis(s+")", left, right-1);
        }
    }

    public List<String> generateParenthesis2(int n) {
        List<String> res = new ArrayList<>();
        // 特判
        if (n == 0) {
            return res;
        }

        dfs("", 0, 0, n, res);
        return res;
    }

    /**
     * @param curStr 当前递归得到的结果
     * @param left   左括号已经用了几个
     * @param right  右括号已经用了几个
     * @param n      左括号、右括号一共得用几个
     * @param res    结果集
     */
    private void dfs(String curStr, int left, int right, int n, List<String> res) {
        if (left == n && right == n) {
            res.add(curStr);
            return;
        }

        // 剪枝
        if (left < right) {
            return;
        }

        if (left < n) {
            dfs(curStr + "(", left + 1, right, n, res);
        }
        if (right < n) {
            dfs(curStr + ")", left, right + 1, n, res);
        }
    }

    public static void main(String[] args) {
        ShengChengKuoHao shengChengKuoHao = new ShengChengKuoHao();
        shengChengKuoHao.generateParenthesis(3);
        System.out.println(shengChengKuoHao.res);
    }
}
