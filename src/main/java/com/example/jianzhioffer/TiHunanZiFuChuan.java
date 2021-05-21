package com.example.jianzhioffer;


/**
 * @author tianzhoubing
 * @date 2021/5/20 10:22
 * @description
 *
 * 请实现一个函数，把字符串 s 中的每个空格替换成"%20"。
 *
 *
 **/
public class TiHunanZiFuChuan {
    public static String replaceSpace(String s) {
        StringBuilder sb=new StringBuilder();
        char[] chars = s.toCharArray();
        for (char aChar : chars) {
            if (aChar == ' '){
                //%20
                sb.append('%');
                sb.append('2');
                sb.append('0');
            }else {
                sb.append(aChar);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String s="We are happy.";
        String s1 = replaceSpace(s);
        System.out.println(s1);
    }
}
