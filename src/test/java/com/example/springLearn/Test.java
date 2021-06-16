package com.example.springLearn;

import java.util.*;

/**
 * @author tianzhoubing
 * @date 2021/5/24 19:05
 * @description
 **/
public class Test {
    //第一题--------------------------------------------------
    static Map<String,String> map=new HashMap<>();

    public String get(String kewords,String value){
        String val = map.get(kewords);
        if (!value.equals(val)){
            return get_new(kewords);
        }
        return val;
    }

    public void set(String kewords,String value,int n){
        map.put(kewords,value);
        myTimer(kewords, n);
    }
    private void myTimer(String kewords,int n){
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                map.put(kewords,"");
            }
        },1000*n,0);
    }

    private String get_new(String kewords){
        System.out.println("query db get keywords");
        return kewords+"data for keywords";
    }

//-------------------------------------------------------------------


    //第二题
    public List<Integer> changeInts(int[] arr){
        int r;
        int l;
        List<Integer> list = new ArrayList<>();
        if (arr.length==0){
            return list;
        }
        if (arr.length == 1||arr[0]<arr[1]){
            list.add(arr[0]);
        }
        for (int i=1;i < arr.length-2; i++){
            l=i-1;
            r=i+1;
            if (arr[i]<arr[r]&&arr[i]>arr[l]){
                list.add(arr[i]);
            }
        }
        return list;
    }
}
