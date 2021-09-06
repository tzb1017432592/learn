package com.example.leetcode.hot;

/**
 * @author tianzhoubing
 * @date 2021/9/7 1:57
 * @description
 *
 * 旋转图像
 **/
public class XunZhaungTuXiang {
    public void rotate(int[][] matrix) {
        int n = matrix[0].length;
        for (int i = 0; i < n/2; i++) {
            for (int j = 0; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n-i-1][j];
                matrix[n-i-1][j] = temp;
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }
}
