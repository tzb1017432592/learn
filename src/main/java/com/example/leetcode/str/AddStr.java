package com.example.leetcode.str;

/**
 * @author tianzhoubing
 * @date 2021/11/25 21:31
 * @description
 **/
public class AddStr {
    public String addStrings(String num1, String num2) {
        if (num1 == null || num2 == null || (num1.equals("0") && num2.equals("0"))) {
            return "0";
        }
        if (num1.equals("0")){
            return num2;
        }
        if (num2.equals("0")){
            return num1;
        }
        int l1 = num1.length() - 1;
        int l2 = num2.length() - 1;
        StringBuilder sb = new StringBuilder();
        int add = 0;
        while (l1 >= 0 || l2 >= 0 || add != 0) {
            int c1 = l1 >= 0 ? num1.charAt(l1) - '0' : 0;
            int c2 = l2 >= 0 ? num2.charAt(l2) - '0' : 0;
            int c = c1 + c2 + add;
            sb.append(c % 10);
            add = c / 10;
            l1--;
            l2--;
        }
        return sb.reverse().toString();
    }
}
