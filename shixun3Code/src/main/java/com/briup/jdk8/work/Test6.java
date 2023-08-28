package com.briup.jdk8.work;

public class Test6 {
    public static void main(String[] args) {
        Action1 action1 = s-> s.toUpperCase().substring(2, 5);
        System.out.println(action1.getValue("hello"));
    }
}
interface Action1{
    public String getValue(String str);
}
