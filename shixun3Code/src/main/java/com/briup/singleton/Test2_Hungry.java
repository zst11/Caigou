package com.briup.singleton;

public class Test2_Hungry {
    private final static Test2_Hungry INSTANCE = new Test2_Hungry();

    private Test2_Hungry(){}
    public static Test2_Hungry getInstance() {
        return INSTANCE;
    }
}
