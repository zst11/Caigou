package com.briup.jdk8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 如何获得stream对象
 */
public class Test9_ToStream {
    public static void main(String[] args) {
//        多值定义
        Stream<String> s1 = Stream.of("a", "b", "c");
//        数组
        int[] arr ={1,3,4,5,7};
        Stream<int[]> s2 = Stream.of(arr);
        IntStream s3 = Arrays.stream(arr); // 这样才能拆开进行流式计算
        String[] strs = {"h","e","l"};
        Stream<String> s4 = Stream.of(strs);
        Stream<String> s5 = Arrays.stream(strs);
//        集合
        ArrayList<String> list = new ArrayList<>();
        Collections.addAll(list,"1","3","5","7");
        Stream<String> s6 = list.stream();
    }
}
