package com.example.leetcode.stack;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * @author tianzhoubing
 * @date 2022/1/7 19:45
 * @description
 *
 * 有效的括号
 *
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
 *
 * 有效字符串需满足：
 *
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 *  
 *
 * 示例 1：
 *
 * 输入：s = "()"
 * 输出：true
 * 示例 2：
 *
 * 输入：s = "()[]{}"
 * 输出：true
 * 示例 3：
 *
 * 输入：s = "(]"
 * 输出：false
 * 示例 4：
 *
 * 输入：s = "([)]"
 * 输出：false
 * 示例 5：
 *
 * 输入：s = "{[]}"
 * 输出：true
 **/
public class YouXiaoKuoHao {
    Map<Character,Character> map = new HashMap<>();
    public boolean isValid(String s) {
        map.put('(',')');
        map.put('[',']');
        map.put('{','}');
        Deque<Character> stack = new LinkedList<>();
        char[] chars = s.toCharArray();
        for (char aChar : chars) {
            if (aChar=='(' || aChar=='['|| aChar=='{'){
                stack.push(aChar);
            }else {
                if (stack.isEmpty()){
                    return false;
                }
                Character pop = stack.pop();
                if (map.get(pop)!=aChar){
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}
