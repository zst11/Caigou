package com.briup.jdk8;

import java.util.Set;
import java.util.TreeSet;
import java.util.function.Function;

public class Test4_Function {
    public static void main(String[] args) {
        String str = "a-b-c-a-b-c";
        Function<String,String[]> f1 = s->s.split("-");
        Function<String[], Set<String>> f2 = s-> {
            TreeSet<String> set = new TreeSet<>();
            for (String s1 : set) {
                set.add(s1);
            }
            return set;
        };
//        执行逻辑，先执行 f1 再执行 f2 andThen 里面是after  ，compose里面是before
        Function<String, Set<String>> f3 = f1.andThen(f2);
        Function<String, Set<String>> f4 = f2.compose(f1);
    }
}
