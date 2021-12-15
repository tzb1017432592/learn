package com.example.leetcode.str;

/**
 * @author tianzhoubing
 * @date 2021/11/25 22:13
 * @description
 **/
public class IntToRoman {
    int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
    public String intToRoman(int num) {
        int length = values.length;
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int value = values[i];
            while (num>=value){
                num = num-value;
                result.append(symbols[i]);
            }
            if (num == 0) {
                break;
            }
        }
        return result.toString();
    }
}
