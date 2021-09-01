package com.example.leetcode.dfs;

/**
 * @author tianzhoubing
 * @date 2021/8/31 22:03
 * @description
 *
 * 岛屿的数量
 **/
public class DaoYuShuLiang {
    public int numIslands(char[][] grid) {
        int y=grid.length;
        int x = grid[0].length;
        int res = 0;
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                if (grid[i][j] == '1') {
                    dfs(grid,j,i);
                    res++;
                }
            }
        }
        return res;
    }

    private void dfs(char[][] grid,int x,int y){
        if (x < 0 || y < 0 || x >= grid[0].length || y >= grid.length){
            return;
        }
        if (grid[y][x]!='1'){
            return;
        }
        grid[y][x]='2';
        dfs(grid,x-1,y);
        dfs(grid,x+1,y);
        dfs(grid,x,y-1);
        dfs(grid,x,y+1);
    }

    private  int a=0;
    private volatile int c = 0;

    public void set() throws InterruptedException {
        //Thread.sleep(500);
        a=5;
        Thread.sleep(500);
        c=1;
    }

    public int get(){
        int d = c;
        return a;
    }

    public static void main(String[] args) {
        DaoYuShuLiang cd =new DaoYuShuLiang();
        Thread t = new Thread(()->{
            try {
                cd.set();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread e = new Thread(()->{

            System.out.println(cd.get());

        });

        t.start();
        e.start();
    }
}
