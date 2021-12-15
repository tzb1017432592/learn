package com.example.leetcode.str;

/**
 * @author tianzhoubing
 * @date 2021/11/26 15:45
 * @description 给你一个字符串 s ，逐个翻转字符串中的所有 单词 。
 * <p>
 * 单词 是由非空格字符组成的字符串。s 中使用至少一个空格将字符串中的 单词 分隔开。
 * <p>
 * 请你返回一个翻转 s 中单词顺序并用单个空格相连的字符串。
 * <p>
 * 说明：
 * <p>
 * 输入字符串 s 可以在前面、后面或者单词间包含多余的空格。
 * 翻转后单词间应当仅用一个空格分隔。
 * 翻转后的字符串中不应包含额外的空格。
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "the sky is blue"
 * 输出："blue is sky the"
 * 示例 2：
 * <p>
 * 输入：s = "  hello world  "
 * 输出："world hello"
 * 解释：输入字符串可以在前面或者后面包含多余的空格，但是翻转后的字符不能包括。
 * 示例 3：
 * <p>
 * 输入：s = "a good   example"
 * 输出："example good a"
 * 解释：如果两个单词间有多余的空格，将翻转后单词间的空格减少到只含一个。
 * 示例 4：
 * <p>
 * 输入：s = "  Bob    Loves  Alice   "
 * 输出："Alice Loves Bob"
 * 示例 5：
 * <p>
 * 输入：s = "Alice does not even like bob"
 * 输出："bob like even not does Alice"
 **/
public class ReverseWord {
    public String reverseWords(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        int left = 0;
        int right = s.length() - 1;
        return reverseBetween(trimAndBetween(left, right, s));
    }

    private String trimAndBetween(int left, int right, String s) {
        while (s.charAt(left) == ' ') {
            left++;
        }
        while (s.charAt(right) == ' ') {
            right--;
        }
        String substring = s.substring(left, right + 1);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < substring.length(); i++) {
            char c = substring.charAt(i);
            if (c != ' ') {
                sb.append(c);
            } else if (i > 0 && substring.charAt(i - 1) != ' ') {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    private String reverseBetween(String s) {
        String[] arr = s.split(" ");
        StringBuilder result = new StringBuilder();
        for (int i = arr.length-1; i >= 0; i--) {
            result.append(arr[i]);
            if (i != 0) {
                result.append(" ");
            }
        }
        return result.toString();
    }

    /*private String reverse(String s) {
        int left = 0;
        int right = s.length() - 1;
        StringBuilder sb = new StringBuilder(s);
        while (left < right) {
            sb.setCharAt(right, s.charAt(left));
            sb.setCharAt(left, s.charAt(right));
            left++;
            right--;
        }
        return sb.toString();
    }*/

}
