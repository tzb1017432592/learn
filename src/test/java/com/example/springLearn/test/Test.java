package com.example.springLearn.test;

import com.alibaba.fastjson.JSONObject;
import com.example.springLearn.LinkedList;
import com.example.springLearn.newlearn.bean.Html;
import com.example.springLearn.newlearn.bean.User;
import com.example.sunfa.maopao.MaoPao;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.commons.lang3.StringUtils;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;

import javax.print.attribute.standard.PrinterURI;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.*;
import java.util.Queue;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Test {
    @org.junit.Test
    public void test03() {
        String join = StringUtils.join("dd:ff:gg:hh:jj", ":");
        System.out.println(System.currentTimeMillis());
    }

    @org.junit.Test
    public void test01() {
        String str = "{\n" +
                "  \"app_usernum\": {\n" +
                "    \"@redis_mark\": \"app_user_num_second\",\n" +
                "    \"@value_type\": \"list\",\n" +
                "    \"@value_len\": \"1\",\n" +
                "    \"@alias\": \"app_usernum\",\n" +
                "    \"@aggoperator\": \"value\",\n" +
                "    \"@then\": {\n" +
                "      \"@value_type\": \"hash\",\n" +
                "      \"@aggoperator\": \"total\"\n" +
                "    }\n" +
                "  }\n" +
                "}";
        JSONObject filejson = JSONObject.parseObject(str);
        for (Map.Entry<String, Object> entry : filejson.entrySet()) {
            System.out.println(entry.getKey());
            System.out.println("=============");
            System.out.println(entry.getValue());
        }
    }


    /**
     * @param str   原先的字符串
     * @param start 反转字符串开始位置的下标
     * @param end   反转字符串结束位置的下标
     * @return 返回反转后的字符串
     */
    private static String strReverse(String str, int start, int end) {
        // 把字符串从头到要反转的位置的字符串截取出来
        // 在此测试代码中，需要反转abcd.  start=5,因此截取的是hello
        String strNew = str.substring(0, start);
        // 利用循环的方式从end位置开始遍历取值，追加到截取的子串后面
        for (int i = end; i >= start; i--) {
            // charAt() 获取指定位置的值
            strNew += str.charAt(i);
        }
        // substring() 截取子串，追加到strNew后面
        strNew += str.substring(end + 1);
        return strNew;
    }

    static void Quick_Sort(int[] arr, int begin, int end) {
        if (begin > end)
            return;
        int tmp = arr[begin];
        int i = begin;
        int j = end;
        while (i != j) {
            while (arr[j] >= tmp && j > i)
                j--;
            while (arr[i] <= tmp && j > i)
                i++;
            if (j > i) {
                int t = arr[i];
                arr[i] = arr[j];
                arr[j] = t;
            }
        }
        arr[begin] = arr[i];
        arr[i] = tmp;
        Quick_Sort(arr, begin, i - 1);
        Quick_Sort(arr, i + 1, end);
    }

    @org.junit.Test
    public void test22() throws UnknownHostException {
        InetAddress inet2 = InetAddress.getByName("www.baidu.com");
        System.out.println(inet2.getHostAddress());
    }

    @org.junit.Test
    public void test23() throws UnknownHostException {
        String str = "www.baidu.com";
        int i = str.indexOf("w");
        char c = str.charAt(0);
        System.out.println(i);
        System.out.println(c);
    }

    @org.junit.Test
    public void test233() throws UnknownHostException {
        String str = "baidu.com.www.";
        int i = str.indexOf(str.charAt(0), 2);
        System.out.println(i);
    }

    public int lengthOfLongestSubstring(String s) {
        // 哈希集合，记录每个字符是否出现过
        Set<Character> occ = new HashSet<Character>();
        int n = s.length();
        // 右指针，初始值为 -1，相当于我们在字符串的左边界的左侧，还没有开始移动
        int rk = -1, ans = 0;
        for (int i = 0; i < n; ++i) {
            if (i != 0) {
                // 左指针向右移动一格，移除一个字符
                occ.remove(s.charAt(i - 1));
            }
            while (rk + 1 < n && !occ.contains(s.charAt(rk + 1))) {
                // 不断地移动右指针
                occ.add(s.charAt(rk + 1));
                ++rk;
            }
            // 第 i 到 rk 个字符是一个极长的无重复字符子串
            ans = Math.max(ans, rk - i + 1);
        }
        return ans;
    }

    public int lengthOfLongestSubstring2(String s) {
        int n = s.length(), ans = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int end = 0, start = 0; end < n; end++) {
            char alpha = s.charAt(end);
            if (map.containsKey(alpha)) {
                start = Math.max(map.get(alpha), start);
            }
            ans = Math.max(ans, end - start + 1);
            map.put(s.charAt(end), end + 1);
        }
        return ans;
    }

    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> hashtable = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; ++i) {
            int c = target - nums[i];
            if (hashtable.containsKey(c)) {
                return new int[]{hashtable.get(c), i};
            }
            hashtable.put(nums[i], i);
        }
        return new int[0];
    }

    @org.junit.Test
    public void test23333() {
        int[] ints = twoSum(new int[]{3, 2, 4}, 6);
        System.out.println(ints[0] + "" + ints[1]);
    }


    @org.junit.Test
    public void test322() throws CloneNotSupportedException {
        User user = new User();
        user.setName("sds");
        user.setId(1);

        int i = (int) 3.5;

        User clone = (User) user.clone();

        System.out.println(clone == user);


    }

    @org.junit.Test
    public void test1231() {
        InetSocketAddress inetSocketAddress = new InetSocketAddress("www.baidu.com", 80);
        System.out.println("Address:" + inetSocketAddress.getAddress());
        System.out.println("域名：" + inetSocketAddress.getHostName());
        System.out.println("ip:" + inetSocketAddress.getAddress().getHostAddress());
    }

    public void quickly(int[] arr, int begin, int end) {
        if (begin > end) {
            return;
        }
        int temp = arr[begin];
        int i = begin, j = end;
        while (j != i) {
            while (temp <= arr[j] && j > i) {
                j--;
            }
            while (temp >= arr[i] && j > i) {
                i++;
            }
            if (j > i) {
                int t = arr[j];
                arr[j] = arr[i];
                arr[i] = t;
            }
        }
        int head = arr[i];
        arr[i] = temp;
        arr[begin] = head;
        quickly(arr, begin, i - 1);
        quickly(arr, i + 1, end);
    }

    public void maopao(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            boolean isend = true;
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    isend = false;
                }
            }
            while (isend) {
                break;
            }
        }
    }

    public int erfeng(int[] arr, int begin, int end, int target) {
        if (begin > end) {
            return -1;
        }
        int min = (begin + end) / 2;
        if (arr[min] == target) {
            return min;
        }
        if (arr[min] > target) {
            return erfeng(arr, begin, min - 1, target);
        } else {
            return erfeng(arr, min + 1, end, target);
        }
    }

    public void select(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }

    @org.junit.Test
    public void test0111() {
        int[] arr = {55, 66, 78, 154, 1, 32, 45, 78, 41, 62, 31, 6, 9, 7, 18, 98, 784, 611, 35, 48, 19, 76, 13};
        quickly(arr, 0, arr.length - 1);
        for (int i : arr) {
            System.out.println(i);
        }
    }

    @org.junit.Test
    public void test01111() {
        int[] arr = {55, 66, 78, 154, 1, 32, 45, 78, 41, 62, 31, 6, 9, 7, 18, 98, 784, 611, 35, 48, 19, 76, 13};
        maopao(arr);
        for (int i : arr) {
            System.out.println(i);
        }
    }

    @org.junit.Test
    public void test0111111() {
        int[] arr = {55, 66, 78, 154, 1, 32, 45, 78, 41, 62, 31, 6, 9, 7, 18, 98, 784, 611, 35, 48, 19, 76, 13};
        select(arr);
        for (int i : arr) {
            System.out.println(i);
        }
    }

    @org.junit.Test
    public void test011111() {
        int[] arr = {55, 66, 78, 154, 1, 32, 45, 78, 41, 62, 31, 6, 9, 7, 18, 98, 784, 611, 35, 48, 19, 76, 13};
        maopao(arr);
        int erfeng = erfeng(arr, 0, arr.length - 1, 62);
        System.out.println(erfeng);
    }

    @org.junit.Test
    public void test023233() {
        Callable<String> result = new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(10000);
                return "success";
            }
        };
        System.out.println(result);

    }

    @org.junit.Test
    public void test01311() {
        System.out.println(81 % 10);
        System.out.println(81 / 10);
    }

    private Integer g = 4;

    private void change(Integer i) {
        i = 5;
        g = 8;
    }

    @org.junit.Test
    public void test01321() {
        change(g);
        System.out.println(g);
    }

    @org.junit.Test
    public void test32() {
        List<String> list = new ArrayList();
        list.add("dd");
        list.add("dd2");
        list.add("dd3");
        list.add("dd4");
        LinkedList linkedList = new LinkedList();

    }

    @org.junit.Test
    public void test321() {
        String[] cs = "abccdefcdh".split("c");
        System.out.println(cs.length);
        double d=5.3e12;
    }

    private class dd{

    }

    public static int multi(int n) {
        if (n == 1) {
            return 1;
        }
        return n * multi(n - 1); //递归调用multi（）方法
    }

    public static void main(String[] args) {
        System.out.println(multi(2));
    }
}
