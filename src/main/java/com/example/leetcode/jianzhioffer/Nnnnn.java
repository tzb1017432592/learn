package com.example.leetcode.jianzhioffer;

/**
 * @author tianzhoubing
 * @date 2021/8/9 11:18
 * @description
 **/
public class Nnnnn {
    public int multi(int n){
        if(n==1){
            return 1;
        }
        return n*multi(n-1);
    }
}
