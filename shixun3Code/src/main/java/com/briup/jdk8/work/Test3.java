package com.briup.jdk8.work;

import javax.naming.Name;
import java.util.HashMap;
import java.util.Set;
import java.util.function.BiConsumer;

public class Test3 {
    public static void main(String[] args) {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("zs", 18);
        map.put("ls", 18);
        map.put("wu", 18);
        map.forEach((s, integer) -> System.out.println(s+":"+integer));
    }
}