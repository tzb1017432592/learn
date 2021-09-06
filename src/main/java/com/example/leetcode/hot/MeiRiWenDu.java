package com.example.leetcode.hot;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author tianzhoubing
 * @date 2021/9/6 23:12
 * @description
 *
 * 每日温度
 *
 * 请根据每日 气温 列表 temperatures ，请计算在每一天需要等几天才会有更高的温度。如果气温在这之后都不会升高，请在该位置用 0 来代替。
 *
 * 示例 1:
 * 输入: temperatures = [73,74,75,71,69,72,76,73]
 * 输出: [1,1,4,2,1,1,0,0]
 *
 * 示例 2:
 * 输入: temperatures = [30,40,50,60]
 * 输出: [1,1,1,0]
 *
 * 示例 3:
 * 输入: temperatures = [30,60,90]
 * 输出: [1,1,0]
 *
 **/
public class MeiRiWenDu {
    public int[] dailyTemperatures(int[] temperatures) {
        int[] res = new int[temperatures.length];
        for (int i = 0; i < temperatures.length; i++) {
            int temp=0;
            for (int j = i+1; j < temperatures.length; j++) {
                temp++;
                if (temperatures[j]>temperatures[i]){
                    res[i] = temp;
                    break;
                }
            }
        }
        return res;
    }

    //单调栈
    public int[] dailyTemperatures2(int[] temperatures) {
        Deque<Integer> stack = new LinkedList<>();
        int[] res = new int[temperatures.length];
        for (int i = 0; i < temperatures.length; i++) {
            while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]){
                Integer pop = stack.pop();
                int len = i - pop;
                res[pop] = len;
            }
            stack.push(i);
        }
        return res;
    }
}
