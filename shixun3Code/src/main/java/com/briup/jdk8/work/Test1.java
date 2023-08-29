package com.briup.jdk8.work;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test1 {
    public static void main(String[] args) {
         Random random = new Random();
        List<Integer> collect = Stream.generate(() -> random.nextInt(100)).limit(20)
                .collect(Collectors.toList());
        System.out.println(collect);
        collect.stream().filter(s->(s & 1 )==0).forEach(System.out::println);
    }
}
