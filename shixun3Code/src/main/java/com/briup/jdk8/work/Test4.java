package com.briup.jdk8.work;

public class Test4 {
    public static void main(String[] args) {
        Thread thread = new Thread(()->{
            System.out.println("2");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();
        System.out.println("1");
    }
}
