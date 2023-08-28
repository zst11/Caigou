package com.briup.jdk8;

public class Test5_MethodRef {
    public static void main(String[] args) {
        Action1 a11 = a->a.charAt(0);
        Action1 a12 = String::length;
        Action1 a13 = String::hashCode;
        Action1 a14 = Test5_MethodRef::len;
        Action1 a15 = Integer::parseInt;
        System.out.println(a11.run("hello"));
        System.out.println(a12.run("hello"));
        System.out.println(a14.run("hello"));
        System.out.println("--------");
//        使用类名引用非静态方法的时候
//        实现的抽象方法的第一个参数作为所属类型的对象
//        从第二个参数开始作为参数列表

    }
    public static int len(String s){
        return s.length();
    }
}
interface Action1{
    int run(String s);
}
