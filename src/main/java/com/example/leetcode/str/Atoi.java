package com.example.leetcode.str;

/**
 * @author tianzhoubing
 * @date 2021/11/15 12:43
 * @description 请你来实现一个 myAtoi(string s) 函数，使其能将字符串转换成一个 32 位有符号整数（类似 C/C++ 中的 atoi 函数）。
 * <p>
 * 函数 myAtoi(string s) 的算法如下：
 * <p>
 * 读入字符串并丢弃无用的前导空格
 * 检查下一个字符（假设还未到字符末尾）为正还是负号，读取该字符（如果有）。 确定最终结果是负数还是正数。 如果两者都不存在，则假定结果为正。
 * 读入下一个字符，直到到达下一个非数字字符或到达输入的结尾。字符串的其余部分将被忽略。
 * 将前面步骤读入的这些数字转换为整数（即，"123" -> 123， "0032" -> 32）。如果没有读入数字，则整数为 0 。必要时更改符号（从步骤 2 开始）。
 * 如果整数数超过 32 位有符号整数范围 [−231,  231 − 1] ，需要截断这个整数，使其保持在这个范围内。具体来说，小于 −231 的整数应该被固定为 −231 ，大于 231 − 1 的整数应该被固定为 231 − 1 。
 * 返回整数作为最终结果。
 * <p>
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/
public class Atoi {
    public static int myAtoi(String s) {
        int length = s.length();
        if (length==0){
            return length;
        }
        int index = 0;
        int flag = 1;
        int result = 0;
        while (s.charAt(index) == ' ') {
            index++;
            if (index >= length){
                return result;
            }
        }
        if (s.charAt(index) == '+') {
            index++;
        } else if (s.charAt(index) == '-') {
            flag = -1;
            index++;
        }
        while (index < length) {
            char c = s.charAt(index);
            if ( c > '9' || c < '0'){
                break;
            }
            if (result>Integer.MAX_VALUE/10 ||(result==Integer.MAX_VALUE/10 && (c-'0') > Integer.MAX_VALUE%10)){
                return Integer.MAX_VALUE;
            }
            if (result<Integer.MIN_VALUE/10 || (result==Integer.MIN_VALUE/10 && (c-'0') > -(Integer.MIN_VALUE%10))){
                return Integer.MIN_VALUE;
            }
            result = result * 10 + flag * (c - '0');
            index++;
        }
        return result;
    }

    public static void main(String[] args) {
        myAtoi("");
    }
}
