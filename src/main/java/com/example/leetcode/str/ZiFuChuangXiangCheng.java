package com.example.leetcode.str;

/**
 * @author tianzhoubing
 * @date 2021/11/24 22:03
 * @description 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
 * <p>
 * 示例 1:
 * <p>
 * 输入: num1 = "2", num2 = "3"
 * 输出: "6"
 * 示例 2:
 * <p>
 * 输入: num1 = "123", num2 = "456"
 * 输出: "56088"
 * 说明：
 * <p>
 * num1 和 num2 的长度小于110。
 * num1 和 num2 只包含数字 0-9。
 * num1 和 num2 均不以零开头，除非是数字 0 本身。
 * 不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理。
 **/
public class ZiFuChuangXiangCheng {
    public String multiply(String num1, String num2) {
        if (num1==null || num2 == null || num1.equals("0") || num2.equals("0")){
            return "0";
        }
        int n1 = num1.length();
        int n2 = num2.length();
        String counts = "0";
        for (int i = n1 - 1; i >= 0; i--) {
            StringBuilder sb = new StringBuilder();
            for (int j = n1 - 1; j > i; j--) {
                sb.append("0");
            }
            int h = 0;
            for (int j = n2 - 1; j >= 0; j--) {
                int c2 = num2.charAt(j) - '0';
                int c1 = num1.charAt(i) - '0';
                int c = c1 * c2 + h;
                sb.append(c % 10);
                h = c / 10;
            }
            if (h != 0) {
                sb.append(h);
            }
            counts = addString(counts, sb.reverse().toString());
        }
        return counts;
    }

    private String addString(String counts, String adds) {
        StringBuilder sb = new StringBuilder();
        int cs = counts.length() - 1;
        int as = adds.length() - 1;
        int add = 0;
        while (cs >= 0 || as >= 0 || add != 0) {
            int x = cs >= 0 ? counts.charAt(cs) - '0' : 0;
            int y = as >= 0 ? adds.charAt(as) - '0' : 0;
            int result = x + y + add;
            sb.append(result % 10);
            add = result / 10;
            cs--;
            as--;
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        ZiFuChuangXiangCheng zif = new ZiFuChuangXiangCheng();
        String multiply = zif.multiply("123", "456");
        System.out.println(multiply);
    }
}
