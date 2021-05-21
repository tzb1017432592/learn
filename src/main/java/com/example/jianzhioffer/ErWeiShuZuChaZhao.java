package com.example.jianzhioffer;

public class ErWeiShuZuChaZhao {
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        int x,y;
        if (matrix==null||matrix.length==0||matrix[0].length==0){
            return false;
        }
        x=matrix.length-1;
        y=0;
        while (matrix[x][y]!=target){
            if (matrix[x][y]>target){
                x--;

            }else if (matrix[x][y]<target){
                y++;

            }
            if (x==0&&y==matrix[0].length){
                return false;
            }

        }
        return true;
    }
}
