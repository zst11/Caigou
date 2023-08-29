package com.briup.jdk8;

/**
 * 最终操作返回的不是stream对象
 * 执行之后就不能再用了，会抛异常
 */

import java.util.Optional;
import java.util.stream.Stream;

public class Test11_FinalAction {
    public static void main(String[] args) {
        Stream<String> stream = Stream.of("test","hello","world","java","tom","C","javascript");
//        最值
        Optional<String> max = stream.max(((o1, o2) -> o1.compareTo(o2)));
        Optional<String> min = stream.min(((o1, o2) -> o1.compareTo(o2)));
//        匹配
        boolean b1 = stream.allMatch(s -> s.startsWith("j"));
        System.out.println(b1);
        boolean b2 = stream.anyMatch(s -> s.startsWith("j"));
        boolean b3 = stream.noneMatch(s -> s.startsWith("j"));
//        取值
        Optional<String> first = stream.findFirst();
        Optional<String> any = stream.findAny();
//        规约
        Optional<String> reduce = stream.reduce(((s1, s2) -> s1 + ":" + s2));
    }
}
