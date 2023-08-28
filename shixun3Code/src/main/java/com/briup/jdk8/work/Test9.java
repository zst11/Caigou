package com.briup.jdk8.work;

import java.util.function.Consumer;

public class Test9 {
    public static void main(String[] args) {
        Consumer<StringBuilder> consumer = s->s.reverse();
        StringBuilder s = new StringBuilder("hello");
        consumer.accept(s);
        System.out.println(s);
    }
}
