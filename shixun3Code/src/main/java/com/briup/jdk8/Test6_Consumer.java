package com.briup.jdk8;

import java.util.function.Consumer;

public class Test6_Consumer {
    public static void main(String[] args) {
        Test6_Consumer test6_consumer = new Test6_Consumer();
        Student stu = new Student("tom");
        Consumer<Student> consumer1 = student -> student.name = "briup_" + student.name;
        Consumer<Student> consumer2 = student -> student.name = student.name +"_"+ System.currentTimeMillis();
        Consumer<Student> consumer3 = consumer1.andThen(consumer2);
        test6_consumer.operStu(stu,consumer3);
        System.out.println(stu.name);
    }
    public void operStu(Student stu, Consumer<Student> consumer){
        consumer.accept(stu);
    }
}
class Student{
    String name;
    public Student(String name){
        this.name = name;
    }
}
