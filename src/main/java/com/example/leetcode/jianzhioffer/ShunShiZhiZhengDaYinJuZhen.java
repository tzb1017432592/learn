package com.example.leetcode.jianzhioffer;

/**
 * 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字。
 *
 * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[1,2,3,6,9,8,7,4,5]
 *
 *
 * 输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
 * 输出：[1,2,3,4,8,12,11,10,9,5,6,7]
 */
public class ShunShiZhiZhengDaYinJuZhen {
    public int[] spiralOrder(int[][] matrix) {
        if (matrix==null||matrix.length==0){
            return new int[]{};
        }
        int r = matrix[0].length-1;
        int b = matrix.length-1;
        int l=0;
        int t=0;
        int index=0;
        int[] res=new int[(r+1)*(b+1)];
        while (r>=l){
            for (int i=l;i<r-l;i++){
                res[index++]=matrix[t][i];
            }
            for (int i=t;i<b-t;i++){
                res[index++]=matrix[i][r];
            }
            for (int i=r;i>l;i--){
                res[index++]=matrix[b][i];
            }
            for (int i=b;i>t;i--){
                res[index++]=matrix[i][l];
            }
            l++;
            r--;
            t++;
            b--;
        }
        return res;
    }
}
