package com.example.lambda.mylambda.example;

import com.example.lambda.bean.UserInfo;
import com.example.lambda.bean.UserInfoDto;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;


import java.io.FileInputStream;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Test {
    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    private static List<UserInfo> userInfos;

    static {
        UserInfo userInfo = new UserInfo(1, "test1", 10, new Date(System.currentTimeMillis() / 2), "男");
        UserInfo userInfo11 = new UserInfo(2, "test2", 110, new Date(System.currentTimeMillis() / 3), "女");
        UserInfo userInfo12 = new UserInfo(3, "test3", 15, new Date(System.currentTimeMillis() / 2), "男");
        UserInfo userInfo13 = new UserInfo(4, "test4", 16, new Date(System.currentTimeMillis() / 4), "女");
        UserInfo userInfo14 = new UserInfo(5, "test5", 17, new Date(System.currentTimeMillis() / 5), "男");
        UserInfo userInfo15 = new UserInfo(6, "test6", 33, new Date(System.currentTimeMillis() / 2), "女");
        UserInfo userInfo16 = new UserInfo(7, "test7", 40, new Date(System.currentTimeMillis() / 4), "男");
        UserInfo userInfo17 = new UserInfo(8, "test8", 56, new Date(System.currentTimeMillis() / 6), "女");
        UserInfo userInfo18 = new UserInfo(9, "test9", 78, new Date(System.currentTimeMillis() / 7), "男");
        UserInfo userInfo19 = new UserInfo(10, "test10", 25, new Date(System.currentTimeMillis() / 6), "女");
        UserInfo userInfo1 = new UserInfo(11, "test11", 87, new Date(System.currentTimeMillis() / 3), "男");
        userInfos = Lists.newArrayList(userInfo, userInfo1, userInfo11, userInfo12, userInfo13, userInfo14, userInfo15, userInfo16, userInfo17, userInfo18, userInfo19);
    }

    @org.junit.Test
    public void test() {
        List<UserInfo> collect = userInfos.stream().collect(Collectors.toList());
        collect.forEach(System.out::println);
    }
    @org.junit.Test
    public void test2() {
        List<Long> collect = userInfos.stream().map(UserInfo::getId).collect(Collectors.toList());
        collect.forEach(System.out::println);
    }
    @org.junit.Test
    public void test3() {
        List<UserInfo> collect = userInfos.stream().filter(i->i.getAge()>10).collect(Collectors.toList());
        collect.forEach(System.out::println);
    }

    @org.junit.Test
    public void test4() {
        List<UserInfo> collect = userInfos.stream().filter(i -> i.getAge() > 10).map(m -> {
            UserInfo userInfo = new UserInfo();
            userInfo.setAge(m.getAge());
            userInfo.setName(m.getName());
            userInfo.setBirthday(m.getBirthday());
            return userInfo;
        }).collect(Collectors.toList());
        collect.forEach(System.out::println);
    }

    @org.junit.Test
    public void test5() {
        List<String> collect = userInfos.stream().filter(i -> i.getAge() > 10).map(UserInfo::getSex).distinct().collect(Collectors.toList());
        collect.forEach(System.out::println);
    }

    @org.junit.Test
    public void test6() {
        Map<String, List<UserInfo>> collect = userInfos.stream().collect(Collectors.groupingBy(UserInfo::getSex));
        collect.forEach((k,v)->{
            System.out.println("========================================================");
            v.forEach(i-> System.out.println("分组的字段："+k+",对应的数据："+i.toString()));
        });
    }

    @org.junit.Test
    public void test7() {
        List<UserInfo> collect = userInfos.stream().sorted(Comparator.comparing(UserInfo::getAge).reversed()).collect(Collectors.toList());
        collect.forEach(System.out::println);
    }

    @org.junit.Test
    public void test8() {
        OptionalInt max = userInfos.stream().mapToInt(UserInfo::getAge).max();
        System.out.println(max.getAsInt());
    }

    @org.junit.Test
    public void test9() {
        OptionalInt min = userInfos.stream().mapToInt(UserInfo::getAge).min();
        System.out.println(min.getAsInt());
    }

    @org.junit.Test
    public void test10() {
        int sum = userInfos.stream().mapToInt(UserInfo::getAge).sum();
        System.out.println(sum);
    }

    @org.junit.Test
    public void test11() {
        long count = userInfos.stream().count();
        System.out.println(count);
    }
    @org.junit.Test
    public void test12() {
        List<UserInfo> collect = userInfos.stream().skip(3).limit(5).collect(Collectors.toList());
        collect.forEach(System.out::println);
    }

    @org.junit.Test
    public void test13() {
        OptionalDouble average = userInfos.stream().mapToDouble(UserInfo::getAge).average();
        System.out.println(average.getAsDouble());
    }

    @org.junit.Test
    public void test14() {
//检查集合中对象的name是否存在test1的
        System.out.println(userInfos.stream().anyMatch(s->StringUtils.equals(s.getName(),"test1")));
//检查集合中对象的name是否全是test1
        System.out.println(userInfos.stream().allMatch(s->StringUtils.equals(s.getName(),"test1")));
//检查集合中对象的name是否全不存在test1
        System.out.println(userInfos.stream().noneMatch(s-> StringUtils.equals(s.getName(),"test1")));
    }

    @org.junit.Test
    public void test15() {
        Map<Boolean, List<UserInfo>> collect = userInfos.stream().collect(Collectors.partitioningBy(i -> i.getAge() > 70));
        collect.forEach((k,v)->{
            System.out.println("========================================================");
            v.forEach(i-> System.out.println("age是否大于70岁："+k+",对应的数据："+i.toString()));
        });
    }

    @org.junit.Test
    public void test16() {
        int sum = userInfos.stream().mapToInt(UserInfo::getAge).sum();
        System.out.println("sum:"+sum);

        int reduce = userInfos.stream().mapToInt(UserInfo::getAge).reduce(10,(a,b)->{
            a+=b;
            return a;
        });
        System.out.println("reduce:"+reduce);
    }

    @org.junit.Test
    public void test17() {
        boolean b = userInfos.stream().anyMatch(i -> StringUtils.isNotBlank(i.getName()));
    }

    @org.junit.Test
    public void test18() {

    }
}
