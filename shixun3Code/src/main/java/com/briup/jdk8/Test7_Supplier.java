package com.briup.jdk8;

import java.util.Random;
import java.util.function.Supplier;

public class Test7_Supplier {
    public static void main(String[] args) {
        Supplier<Integer> supplier = ()->{
            Random random = new Random();
            return random.nextInt(100);
        };
        System.out.println(supplier.get());
    }
}
