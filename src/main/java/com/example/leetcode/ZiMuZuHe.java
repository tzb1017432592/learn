package com.example.leetcode;

import java.util.*;

/**
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
 * <p>
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 * <p>
 * 输入："23"
 * 输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 */
public class ZiMuZuHe {
    public static List<String> letterCombinations(String digits) {
        List<String> results = new ArrayList<>();
        Map<Character, String> map = new HashMap<>();
        map.put('2', "abc");
        map.put('3', "def");
        map.put('4', "ghi");
        map.put('5', "jkl");
        map.put('6', "mno");
        map.put('7', "pqrs");
        map.put('8', "tuv");
        map.put('9', "wxyz");
        backTrack(digits, map, results, 0,new StringBuilder());
        return results;
    }

    private static void backTrack(String digits, Map<Character, String> map, List<String> results, int index,StringBuilder builder) {
        if (digits.length() == index) {
            results.add(builder.toString());
        }else {
            char inputChar = digits.charAt(index);
            String vstr = map.get(inputChar);
            for (int i=0;i<vstr.length();i++){
                char c = vstr.charAt(i);
                builder.append(c);
                backTrack(digits,map,results,index+1,builder);
                builder.deleteCharAt(index);
            }
        }
    }

  /*  public static List<String> letterCombinations2(String digits) {
        List<String> combinations = new ArrayList<String>();
        if (digits.length() == 0) {
            return combinations;
        }
        Map<Character, String> phoneMap = new HashMap<Character, String>() {{
            put('2', "abc");
            put('3', "def");
            put('4', "ghi");
            put('5', "jkl");
            put('6', "mno");
            put('7', "pqrs");
            put('8', "tuv");
            put('9', "wxyz");
        }};
        backtrack(combinations, phoneMap, digits, 0, new StringBuffer());
        return combinations;
    }

    public static void backtrack(List<String> combinations, Map<Character, String> phoneMap, String digits, int index, StringBuffer combination) {
        if (index == digits.length()) {
            combinations.add(combination.toString());
        } else {
            char digit = digits.charAt(index);
            String letters = phoneMap.get(digit);
            int lettersCount = letters.length();
            for (int i = 0; i < lettersCount; i++) {
                combination.append(letters.charAt(i));
                backtrack(combinations, phoneMap, digits, index + 1, combination);
                combination.deleteCharAt(index);
            }
        }
    }*/

    public static void main(String[] args) {
         System.out.println(letterCombinations("2222"));
    }

}
