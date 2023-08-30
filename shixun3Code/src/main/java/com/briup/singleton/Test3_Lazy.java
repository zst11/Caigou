package com.briup.singleton;

public class Test3_Lazy {
    private static Test3_Lazy INSTANCE;
    private Test3_Lazy(){}
    public static Test3_Lazy getInstance(){
        if (INSTANCE == null) INSTANCE = new Test3_Lazy();
        return INSTANCE;
    }
}
