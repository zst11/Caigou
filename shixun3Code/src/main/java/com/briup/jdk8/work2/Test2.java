package com.briup.jdk8.work2;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test2 {
    public static void main(String[] args) {
        String[] strings = {"水煮鱼","酸菜鱼","麻辣鱼",
                "麻婆豆腐","回锅肉","鱼香肉丝","水煮肉片","宫保鸡丁",
                "剁椒 鱼头","夫妻肺片"};
        Stream<String> s1 = Stream.of(strings);
        List<String> list = s1.filter(s -> s.length() == 3).filter(s -> s.contains("鱼")).collect(Collectors.toList());
        System.out.println(list);
    }
}
