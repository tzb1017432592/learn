package com.example.leetcode.jianzhioffer;

/**
 * 在一个 n * m 的二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
 * 请完成一个高效的函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 *
 * 示例:
 *
 * 现有矩阵 matrix 如下：
 *
 * [
 *   [1,   4,  7, 11, 15],
 *   [2,   5,  8, 12, 19],
 *   [3,   6,  9, 16, 22],
 *   [10, 13, 14, 17, 24],
 *   [18, 21, 23, 26, 30]
 * ]
 * 给定 target=5，返回true。
 * 给定target=20，返回false。
 *
 */
public class ErWeiShuZuChaZhao {
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        int x,y;
        if (matrix==null||matrix.length==0||matrix[0].length==0){
            return false;
        }
        x=matrix[0].length-1;
        y=0;
        while (matrix[y][x]!=target){
            if (matrix[y][x]>target){
                x--;

            }else if (matrix[y][x]<target){
                y++;

            }
            if (x<0||y>matrix.length-1){
                return false;
            }

        }
        return true;
    }
}
