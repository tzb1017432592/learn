package com.example.sunfa.maopao;

import java.util.Arrays;

public class MaoPao {
    private static void sort1(int[] array) {
        // array.length - 1是因为最后一轮不需要排序
        for (int i = 0; i < array.length - 1; i++) {
            System.out.println("第" + (i + 1) + "趟");
            // array.length - i是因为每一轮都能确定排序好一个数
            for (int j = 0; j < array.length - 1 - i; j++) {
                int temp = 0;
                if (array[j] > array[j + 1]) {
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
                System.out.println("  第" + (j + 1) + "次：" + Arrays.toString(array));
            }
        }
    }
    private static void sort3(int[] array) {
        // 用来交换的临时数
        int temp = 0;
        // 最后一次交换的下标
        int lastSwapIndex = 0;
        // 无序数组的边界，每次比较比到这里为止
        int arrBoundary = array.length - 1;
        for (int i = 0; i < array.length - 1; i++) {
            System.out.println("第" + (i + 1) + "趟");
            // 优化冒泡排序，增加判断位，有序标记，每一轮的初始是true
            boolean flag = true;
            for (int j = 0; j < arrBoundary; j++) {
                // 找最小数，如果前一位比后一位大，则交换位置
                if (array[j] > array[j + 1]) {
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    // 有元素交换，所以不是有序，标记变为false
                    flag = false;
                    // 最后一次交换元素的位置
                    lastSwapIndex = j;
                }
                System.out.println("  第" + (j + 1) + "次：" + Arrays.toString(array));
            }
            // 把最后一次交换元素的位置赋值给无序数组的边界
            arrBoundary = lastSwapIndex;
            // 说明上面内层for循环中，没有交换任何元素，直接跳出外层循环
            if (flag) {
                break;
            }
        }
    }
}
