package com.briup.client;

import com.briup.smart.env.client.Client;
import com.briup.smart.env.client.Gather;
import com.briup.smart.env.entity.Environment;
import com.briup.smart.env.impl.ConfigurationImpl;
import com.briup.smart.env.util.LogImpl;

import java.util.Collection;

public class ClientMain {
    public static void main(String[] args) throws Exception {
        LogImpl log = new LogImpl();
        // 执行客户端发送过来的采集的数据
        log.debug("客户端开启");
        Client client = ConfigurationImpl.getInstance().getClient();
        Gather gather = ConfigurationImpl.getInstance().getGather();
        try {
            // 采集（采集过程可能不执行），定时..
            Collection<Environment> list = gather.gather();
            client.send(list);
        } catch (Exception e) {


            log.debug("客户端异常，程序终止");
            e.printStackTrace();
        }
        log.debug("客户端数据发送成功");
    }
}
