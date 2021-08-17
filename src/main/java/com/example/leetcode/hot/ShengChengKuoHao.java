package com.example.leetcode.hot;

import java.util.ArrayList;
import java.util.List;

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

    public static void main(String[] args) {
        ShengChengKuoHao shengChengKuoHao = new ShengChengKuoHao();
        shengChengKuoHao.generateParenthesis(3);
        System.out.println(shengChengKuoHao.res);
    }
}
