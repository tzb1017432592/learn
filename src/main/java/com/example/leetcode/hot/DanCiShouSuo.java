package com.example.leetcode.hot;

/**
 * @author tianzhoubing
 * @date 2021/8/30 18:00
 * @description 单词搜索
 * <p>
 * 给定一个 m x n 二维字符网格board 和一个字符串单词word 。如果word 存在于网格中，返回 true ；否则，返回 false 。
 * <p>
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 * <p>
 * 示例 1：
 * 输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
 * 输出：true
 * <p>
 * 示例 2：
 * 输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "SEE"
 * 输出：true
 * <p>
 * 示例 3：
 * 输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCB"
 * 输出：false
 **/
public class DanCiShouSuo {
    public static boolean exist(char[][] board, String word) {
        int y = board.length;
        int x = board[0].length;
        boolean[][] checks = new boolean[y][x];
        if (word.equals(board[0][0]+"")){
            return true;
        }
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                if (dfs(checks, board, word, i, j, 0)){
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean dfs(boolean[][] checks, char[][] board, String word, int i, int j, int len) {
        if (word.length() == len) {
            return true;
        }
        if (i < 0 || i >= board.length
                || j < 0 || j >= board[0].length
                || board[i][j] != word.charAt(len)) {
            return false;
        }
        if (checks[i][j]){
            return false;
        }
        checks[i][j] = true;
        boolean res = dfs(checks, board, word, i - 1, j, len + 1) || dfs(checks, board, word, i + 1, j, len + 1)
                || dfs(checks, board, word, i, j - 1, len + 1) || dfs(checks, board, word, i, j + 1, len + 1);
        checks[i][j] = false;
        return res;
    }

    public static void main(String[] args) {
        char[][] rs=new char[][]{{'a'}};
        exist(rs,"b");
    }
}
