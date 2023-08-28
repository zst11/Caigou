package com.briup.jdk8.work;

import java.util.function.Function;
import java.util.function.ToIntFunction;

public class Test5 {
    public static void main(String[] args) {
        Action a = (String::length);
        Action a1 = Test5::toNum;
        System.out.println(a.toNum("hello"));
        System.out.println(a1.toNum("he"));
    }
    public static int toNum(String s){
        return s.length();
    }
}
interface Action{
    int toNum(String s);
}
