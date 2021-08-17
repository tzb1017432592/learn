package com.example.leetcode.hot;

/**
 * @author tianzhoubing
 * @date 2021/8/17 16:47
 * @description
 *
 * 盛最多水的容器
 *
 * 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点(i,ai) 。在坐标内画 n 条垂直线，
 * 垂直线 i的两个端点分别为(i,ai) 和 (i, 0) 。找出其中的两条线，使得它们与x轴共同构成的容器可以容纳最多的水。
 *
 * 说明：你不能倾斜容器。
  双指针
 **/
public class ChengZuiDuoShuiDeRongQi {
    public int maxArea(int[] height) {
        int start=0;
        int end=height.length-1;
        int maxArea=0;
        while (start<end){
            if (height[start] == height[end]){
                maxArea=Math.max(height[start]*(end - start),maxArea);
                start++;
            }
            else if (height[start] > height[end]){
                maxArea = Math.max(height[end]*(end - start), maxArea);
                end--;
            }
            else if (height[start] < height[end]){
                maxArea = Math.max(height[start]*(end - start), maxArea);
                start++;
            }
        }
        return maxArea;
    }
}
