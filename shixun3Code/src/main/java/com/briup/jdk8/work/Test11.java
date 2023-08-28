package com.briup.jdk8.work;

import java.util.function.Function;

public class Test11 {
    public static void main(String[] args) {
        Function<String,Integer> f1 = s->Integer.parseInt(s) + 10;
        Function<Integer,String> f2 = s->String.valueOf(s);
        Function<String,String> f3 = f1.andThen(f2);
        String s = f3.apply("123");
        System.out.println(s);
    }
}
