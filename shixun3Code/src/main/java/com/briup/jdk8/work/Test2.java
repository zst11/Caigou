package com.briup.jdk8.work;

import java.util.ArrayList;
import java.util.function.Predicate;

public class Test2 {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        ArrayList<String> list1 = new ArrayList<>();
        list.add("hello");
        list.add("world");
        list.add("hello");
        list.add("java");
        list = filter(list, e -> !list1.contains(e));
        System.out.println(list);
    }
    public static ArrayList<String> filter(ArrayList<String> list, Predicate<String> predicate){
        ArrayList<String> list1 = new ArrayList<>();
        for (String s : list) {
            if (predicate.test(s)){
                list1.add(s);
            }
        }
        return list1;
    }
}
