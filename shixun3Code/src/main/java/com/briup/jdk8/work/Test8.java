package com.briup.jdk8.work;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Predicate;

public class Test8 {
    public static void main(String[] args) {
        String[] arr = {"卢本伟,男","五五开,女","white,男"};
        Predicate<String> p1 = i->i.length()==3;
        Predicate<String> p2 = i->"男".equals(i);
        Predicate<String> p = p1.and(p2);
        ArrayList<String> list = filter(arr, p1,p2);
        System.out.println(list);
    }
    public static ArrayList<String> filter(String[] arr, Predicate<String> p1,Predicate<String> p2){
        ArrayList<String> list = new ArrayList<>();
        for (String s : arr) {
            String[] split = s.split(",");
            if (p1.test(split[0])&&p2.test(split[1]))
                list.add(s);
        }
        return list;
    }
}
