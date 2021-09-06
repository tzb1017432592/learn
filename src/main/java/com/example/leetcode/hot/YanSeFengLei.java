package com.example.leetcode.hot;

/**
 * @author tianzhoubing
 * @date 2021/9/6 18:41
 * @description
 *
 * 颜色分类
 *
 * 给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
 *
 * 此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
 *
 *
 *
 * 示例 1：
 * 输入：nums = [2,0,2,1,1,0]
 * 输出：[0,0,1,1,2,2]
 *
 * 示例 2：
 * 输入：nums = [2,0,1]
 * 输出：[0,1,2]
 *
 * 示例 3：
 * 输入：nums = [0]
 * 输出：[0]
 *
 * 示例 4：
 * 输入：nums = [1]
 * 输出：[1]
 **/
public class YanSeFengLei {
    public void sortColors(int[] nums) {
        int p0=0,p2=nums.length - 1;
        for (int i = 0; i <= p2; i++) {
            while (i<p2&&nums[i]==2){
                int temp = nums[i];
                nums[i] = nums[p2];
                nums[p2] = temp;
                p2--;
            }
            if (nums[i] == 0){
                int temp = nums[i];
                nums[i] = nums[p0];
                nums[p0] = temp;
                p0++;
            }

        }
    }
}
