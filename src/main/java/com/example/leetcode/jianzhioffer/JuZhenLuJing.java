package com.example.leetcode.jianzhioffer;

/**
 * 矩阵中的路径
 * <p>
 * 给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。
 * <p>
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 * <p>
 *  
 * <p>
 * 例如，在下面的 3×4 的矩阵中包含单词 "ABCCED"（单词中的字母已标出）。
 * <p>
 * 输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
 * 输出：true
 * <p>
 * 输入：board = [["a","b"],["c","d"]], word = "abcd"
 * 输出：false
 *
 * 深度优先
 */
public class JuZhenLuJing {
    public boolean exist(char[][] board, String word) {
        boolean[][] bs = new boolean[board.length][board[0].length];
        char[] chars = word.toCharArray();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (dfs(board, chars, bs, i, j, 0))
                    return true;
            }
        }
        return false;
    }

    private boolean dfs(char[][] board, char[] word, boolean[][] bs, int i, int j, int k) {
        if (i >= board.length || i < 0 || j >= board[0].length || j < 0 || board[i][j] != word[k] || bs[i][j]) {
            return false;
        }
        if (k == word.length - 1) return true;
        bs[i][j] = true;
        boolean res = dfs(board, word, bs, i + 1, j, k + 1) || dfs(board, word, bs, i - 1, j, k + 1) ||
                dfs(board, word, bs, i, j + 1, k + 1) || dfs(board, word, bs, i, j - 1, k + 1);
        bs[i][j] = false;
        return res;
    }

}
