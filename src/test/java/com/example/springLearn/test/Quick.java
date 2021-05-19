package com.example.springLearn.test;

/**
 * @author tianzhoubing
 * @date 2021/5/19 10:29
 * @description
 **/
public class Quick {
    public void Quick_Sort(int[] arr,int begin,int end){
        int temp = arr[begin];
        int i=begin;
        int j=end;
        while (i!=j){
            while (arr[begin]<=arr[j]&&j>i){
                j--;
            }
            while (arr[begin]>arr[i]&&j > i){
                i++;
            }
            if (i!=j){
                int t=arr[i];
                arr[i] = arr[j];
                arr[j] = t;
            }
        }
        arr[begin] = arr[j];
        arr[j]=temp;
        Quick_Sort(arr,begin,j-1);
        Quick_Sort(arr,j+1,end);
    }
}
