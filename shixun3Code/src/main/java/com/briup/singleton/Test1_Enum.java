package com.briup.singleton;

public class Test1_Enum {
    enum Singleton{
        INSTANCE;
        public void test(){
            System.out.println("test");
        }
    }
}
