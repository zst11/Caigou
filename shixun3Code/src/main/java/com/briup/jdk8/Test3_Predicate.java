package com.briup.jdk8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

public class Test3_Predicate {
    public static void main(String[] args) {
//        Test3_Predicate test3Predicate = new Test3_Predicate();
        Integer[] arr = {11,22,66,77};
        arr = filter(arr,e->e>50);
        System.out.println(Arrays.toString(arr));
    }
    public static Integer[] filter(Integer[] arr, Predicate<Integer> p){
        ArrayList<Integer> list = new ArrayList<>();
        for (Integer integer : arr) {
            if (p.test(integer)) list.add(integer);
        }
        return list.toArray(new Integer[0]);
    }
}
