package com.briup.jdk8.work;

import java.util.ArrayList;
import java.util.Random;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Test1 {
    public static void main(String[] args) {
        Supplier<Integer> supplier = ()->{
            Random random = new Random();
            return random.nextInt(20);
        };
        print(supplier,e->(e & 1)== 0);
    }
    public static void print(Supplier<Integer> supplier, Predicate<Integer> p){
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add(supplier.get());
        }
        ArrayList<Integer> list1 = new ArrayList<>();
        for (Integer i:list){
            if (p.test(i)){
                list1.add(i);
            }
        }
        for (Integer i : list1) {
            System.out.print(i + "\t");
        }
    }
}
