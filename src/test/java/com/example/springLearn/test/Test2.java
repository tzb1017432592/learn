package com.example.springLearn.test;

import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class Test2 {
    public static void main(String[] args) {
        List<String> words= Arrays.asList("Hello","World");
        List<String[]> collect = words.stream()
                .map(word -> word.split(""))
                .distinct()
                .collect(toList());

        words.stream()
                .map(word -> word.split(""))
                .map(Arrays::stream)
                .distinct()
                .collect(toList());

        words.stream()
                .map(w -> w.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(Collectors.toList());

        List<Integer> numbers1 = Arrays.asList(1, 2, 3);
        List<Integer> numbers2 = Arrays.asList(3, 4);
        List<int[]> pairs =
                numbers1.stream()
                        .flatMap(i -> numbers2.stream()
                                .map(j -> new int[]{i, j})
                        ).collect(toList());
        for (String[] strings : collect) {
            for (String string : strings) {
                System.out.println(string);
            }
        }

        List<Integer> list=new ArrayList<>();
    }
}
