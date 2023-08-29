package com.briup.jdk8.work2;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test1 {
    public static void main(String[] args) {
        Set<Person> list = new HashSet<>();
        Person p1 = new Person();
        Person p2 = new Person(1, "tom");
        Person p3 = new Person(2, "jerry");
        Person p4 = new Person(3, "bob");
        Person p5 = new Person(4, "lilith");
        Person p6 = new Person(5, "张三峰");
        Person p7 = new Person(6, "张无忌");
        Collections.addAll(list,p1,p2,p3,p4,p5);
//        limit skip map sorted filter distinct
        Set<Person> list1 = list.stream().filter(s ->{
            if (s.getName()==null||"".equals(s.getName())) return false;
            return s.getName().length() == 3;})
                .collect(Collectors.toSet());
        Set<Person> list2 = list1.stream().limit(3).collect(Collectors.toSet());
        Set<Person> list3 = list.stream().filter(s -> {
            if (s.getName()==null||"".equals(s.getName())) return false;
            return s.getName().startsWith("张");})
                .collect(Collectors.toSet());
        Set<Person> list4 = list3.stream().skip(2).collect(Collectors.toSet());
        Stream<Person> concat = Stream.concat(list2.stream(), list4.stream());
//        concat.forEach(System.out::println);
        String[] names = {"李四","李白","李磊"};
        Stream<String> nameStream = Stream.of(names);
        Stream<Person> personStream = nameStream.map(s -> {
            Person person = new Person();
            person.setName(s);
            return person;
        });
        Set<Person> result = Stream.concat(personStream, concat).collect(Collectors.toSet());
        result.forEach(System.out::println);
    }
}
class Person{
    private Integer id;
    private String name;

    public Person() {
    }

    public Person(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * 获取
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * 设置
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return "Person{id = " + id + ", name = " + name + "}";
    }
}
