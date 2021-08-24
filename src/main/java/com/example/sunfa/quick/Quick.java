package com.example.sunfa.quick;

public class Quick {
    static void Quick_Sort(int[] arr, int begin, int end){
        if(begin > end)
            return;
        int tmp = arr[begin];
        int i = begin;
        int j = end;
        while(i != j){
            while(arr[j] >= tmp && j > i)
                j--;
            while(arr[i] <= tmp && j > i)
                i++;
            if(j > i){
                int t = arr[i];
                arr[i] = arr[j];
                arr[j] = t;
            }
        }
        arr[begin] = arr[i];
        arr[i] = tmp;
        Quick_Sort(arr, begin, i-1);
        Quick_Sort(arr, i+1, end);
    }
    static void sort(int[] arr,int star,int end){
        if(star > end){
            return;
        }
        int temp = arr[star];
        int i = star;
        int j = end;
        while(i != j){
            while(i < j && arr[j] >= temp){
                j--;
            }
            while(i < j&& arr[i] <= temp){
                i++;
            }
            if(i < j){
                int t = arr[j];
                arr[j] = arr[i];
                arr[i] = t;
            }
        }
        arr[star] = arr[i];
        arr[i] = temp;
        sort(arr,star,i-1);
        sort(arr,i+1,end);

    }


    public static void main(String[] args) {
        int[] result = new int[]{5,2,3,1,4};
        sort(result,0, result.length-1);
        for (int i : result) {
            System.out.println(i);
        }
    }
}
