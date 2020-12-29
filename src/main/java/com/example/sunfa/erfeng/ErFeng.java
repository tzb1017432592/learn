package com.example.sunfa.erfeng;

public class ErFeng {
    private static int binarySearch(int[] array, int aim, int left, int right) {
        if (aim < array[left] || aim > array[right]) {
            return -1;
        }
        // 找中间值
        int mid = (left + right) / 2;
        if (array[mid] == aim) {
            return mid;
        } else if (array[mid] > aim) {
            //如果中间值大于要找的值则从左边一半继续递归
            return binarySearch(array, aim, left, mid - 1);
        } else {
            //如果中间值小于要找的值则从右边一半继续递归
            return binarySearch(array, aim, mid + 1, array.length-1);
        }
    }
    private static int binarySearch(int[] array, int aim) {
        // 数组最小索引值
        int left = 0;
        // 数组最大索引值
        int right = array.length - 1;
        int mid;
        while (left <= right) {
            mid = (left + right) / 2;
            // 若查找数值比中间值小，则以整个查找范围的前半部分作为新的查找范围
            if (aim < array[mid]) {
                right = mid - 1;
                // 若查找数值比中间值大，则以整个查找范围的后半部分作为新的查找范围
            } else if (aim > array[mid]) {
                left = mid + 1;
                // 若查找数据与中间元素值正好相等，则放回中间元素值的索引
            } else {
                return mid;
            }
        }
        return -1;
    }
}
