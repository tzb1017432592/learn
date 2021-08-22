package com.example.leetcode.hot;

/**
 * 接雨水
 */
public class JieYuShui {
    public int trap(int[] height) {
        int left=0,leftMax=0;
        int right=height.length-1,rightMax=0;
        int max=0;
        while (left<right){
            leftMax=Math.max(leftMax,height[left]);
            rightMax=Math.max(rightMax,height[right]);
            if (height[left]<height[right]){
                max+=leftMax-height[left];
                left++;
            }else {
                max+=rightMax-height[right];
                right--;
            }
        }
        return max;
    }
}
