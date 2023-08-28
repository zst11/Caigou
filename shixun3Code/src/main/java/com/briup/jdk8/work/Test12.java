package com.briup.jdk8.work;

import java.util.Arrays;
import java.util.function.Supplier;

public class Test12 {
    public static void main(String[] args) {
        Supplier<Integer> supplier = ()->{
            int[] arr = {2,3,4,52,333,23};
            Arrays.sort(arr);
            return arr[arr.length -1];
        };
        System.out.println(supplier.get());
    }
}
