package com.briup.server;

import com.briup.smart.env.impl.ConfigurationImpl;
import com.briup.smart.env.server.Server;
import com.briup.smart.env.util.Log;

public class ServerMain {
    public static void main(String[] args) {
        // 服务器开启
        Log log = null;
        try {
            log = ConfigurationImpl.getInstance().getLogger();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (log!=null){
            log.debug("服务器开启...");
        }
//        System.out.println("服务器开启");
        Server server = null;
        try {
            server = ConfigurationImpl.getInstance().getServer();
            server.receive();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
