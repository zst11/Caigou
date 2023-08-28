package com.briup.jdk8.work;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class Test10 {
    public static void main(String[] args) {
        String[] arr = {"卢本伟,男","五五开,女","white,男"};
        Consumer<String> c1 = s-> System.out.print(s.split(",")[0]+":"+
                s.split(",")[1]+"\t");
        Consumer<String> c2 = s-> System.out.println("姓名："+ s.split(",")[0]+","
                +"性别："+s.split(",")[1]);
        Consumer<String> c3 = c1.andThen(c2);
        print(arr,c3);
    }
    public static void print(String[] arr,Consumer<String> c){
        for (String s : arr) {
            c.accept(s);
        }
    }
}
