package com.example.sunfa.selectSort;

import java.util.Arrays;

public class SelectSort {
    private static void selectSort(int[] array) {
        //判断数组为空或为一个元素的情况
        if (null == array || array.length <= 1) {
            return;
        }
        for (int i = 0; i < array.length - 1; i++) {
            int tempIndex = i; // 当前最小元素的索引
            int temp = array[i]; // 临时变量为当前最小元素
            // 循环遍历待排序的数组
            for (int j = i + 1; j < array.length; j++) {
                // 如果发现有比这个最小位置处的元素更小的元素，则将那个更小的元素的下标赋给临时变量
                if (temp > array[j]) {
                    temp = array[j];
                    tempIndex = j;
                }
            }
            // 如果临时变量发生改变，则说明有比当前外层循环位置更小的元素，需要将这两个元素交换位置
            if (tempIndex != i) {
                array[tempIndex] = array[i];
                array[i] = temp;
            }
            System.out.println("   第" + (i + 1) + "趟后:" + Arrays.toString(array));
        }
    }
}
