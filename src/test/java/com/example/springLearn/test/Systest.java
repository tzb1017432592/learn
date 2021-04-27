package com.example.springLearn.test;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Systest {
    public int[] twoSum(int[] nums, int target) {
        int[] results;
        Map<Integer,Integer> map=new HashMap<>();
        for (int i=0;i<nums.length;i++){
            int num=target-nums[i];
            if (map.containsKey(num)){
                results= new int[]{map.get(num), i};
                return results;
            }
            map.put(nums[i],i);
        }
        return null;
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        while(sc.hasNext())
        {
            System.out.println("输出："+sc.next());
        }

    }
}
