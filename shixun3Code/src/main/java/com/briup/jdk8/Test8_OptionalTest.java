package com.briup.jdk8;

import java.util.Objects;
import java.util.Optional;

/**
 * jdk8使用optional来处理空指针问题
 */
public class Test8_OptionalTest {
    public static void main(String[] args) throws Exception {
        Optional<String> op1 = Optional.of("不允许为空的值"); // 不允许为空
        Optional<String> op2 = Optional.ofNullable(null); // 允许为空
        if (op1.isPresent()){
            System.out.println(op1.get());
        }
        if (op2.isPresent()){
            System.out.println(op2.get());
        }
        op1.ifPresent(System.out::println);
        op2.ifPresent(s->System.out.println(s));

        System.out.println(op1.orElse("op1的默认值"));
        System.out.println(op2.orElse("op2的默认值"));

        System.out.println(op1.orElseGet(() -> "op1supplier的默认值"));
        System.out.println(op2.orElseGet(() -> op1.get() + "_briup"));

        op1.orElseThrow(()-> new RuntimeException("op1出现异常"));
        op2.orElseThrow(Exception::new);

//        过滤:提供一个条件，满足条件就返回自己，不满足条件就返回一个空的optional
        System.out.println(op1.filter(s -> s.length() < 20));
        System.out.println(op2.filter(Objects::isNull));

//        映射 map 从T类型转到任意类型
        String str = op1.map(s->s.length())
                .map(s->String.valueOf(s))
                .orElse("转换后的默认值");
//        扁平化映射 flatMap 从T类型转换到任意的optional
//        功能和map类似，保证转换的值解决空指针问题
        Optional<String> s1 = op2.flatMap(s -> Optional.of(s));

    }
}
