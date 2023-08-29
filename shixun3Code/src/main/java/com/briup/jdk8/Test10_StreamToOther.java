package com.briup.jdk8;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * stream 转其他类型
 */
public class Test10_StreamToOther {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        Collections.addAll(list,"hello","world");
        Stream<String> s1 = list.stream(); // stream 流只能用一次，再次使用会报错
//        转数组
//        String[] strings = s1.toArray(s -> new String[list.size()]);
////        转集合
////        默认的list
//        List<String> c1 = s1.collect(Collectors.toList());
////        默认的set
//        Set<String> c2 = s1.collect(Collectors.toSet());
////        转指定list
//        ArrayList<String> c3 = s1.collect(Collectors.toCollection(() -> new ArrayList<>()));
////        转指定的set
//        HashSet<String> c4 = s1.collect(Collectors.toCollection(() -> new HashSet<>()));
//
////        转字符串，需用特定的字符进行连接
//        String str1 = s1.collect(Collectors.joining());
//        String str2 = s1.collect(Collectors.joining("-"));
        String str3 = s1.collect(Collectors.joining(",", "[", "]"));
        System.out.println(str3);
    }
}
