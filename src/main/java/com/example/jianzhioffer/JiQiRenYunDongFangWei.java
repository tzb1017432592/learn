package com.example.jianzhioffer;

/**
 * 地上有一个m行n列的方格，从坐标 [0,0] 到坐标 [m-1,n-1] 。一个机器人从坐标 [0, 0] 的格子开始移动，
 * 它每次可以向左、右、上、下移动一格（不能移动到方格外），
 * 也不能进入行坐标和列坐标的数位之和大于k的格子。例如，当k为18时，机器人能够进入方格 [35, 37] ，
 * 因为3+5+3+7=18。但它不能进入方格 [35, 38]，因为3+5+3+8=19。请问该机器人能够到达多少个格子？
 *
 *
 * 输入：m = 2, n = 3, k = 1
 * 输出：3
 *
 *
 * 输入：m = 3, n = 1, k = 0
 * 输出：1
 */
public class JiQiRenYunDongFangWei {
    int m, n, k;
    boolean[][] visited;
    public int movingCount(int m, int n, int k) {
        this.m = m; this.n = n; this.k = k;
        this.visited = new boolean[m][n];
        return dfs(0, 0, 0, 0);
    }
    public int dfs(int x, int y, int sx, int sy) {
        if(x >= m || y >= n || k < sx + sy || visited[x][y]) return 0;
        visited[x][y] = true;
        int right=x+1;
        int down=y+1;
        return 1 + dfs(right, y, move(right), move(sy)) + dfs(x, down, move(sx), move(down));
    }

    public int move(int x){
        int s = 0;
        while(x != 0) {
            s += x % 10;
            x = x / 10;
        }
        return s;
    }

    public static void main(String[] args) {
        System.out.println(5%10);
        System.out.println(5/10);
    }
}
