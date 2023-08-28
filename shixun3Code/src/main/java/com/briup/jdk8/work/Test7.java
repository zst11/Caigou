package com.briup.jdk8.work;

public class Test7 {
    public static void main(String[] args) {
        Action2<Long,Long> action2 = (l,n)->l + n;
        System.out.println(action2.test(10L,10L));
    }
}
interface Action2<T,R>{
    R test(T t,T x);
}
