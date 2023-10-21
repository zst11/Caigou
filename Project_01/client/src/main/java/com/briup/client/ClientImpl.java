package com.briup.client;

import com.briup.smart.env.Configuration;
import com.briup.smart.env.client.Client;
import com.briup.smart.env.entity.Environment;
import com.briup.smart.env.support.ConfigurationAware;
import com.briup.smart.env.support.PropertiesAware;
import com.briup.smart.env.util.Log;

import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Collection;
import java.util.Properties;

public class ClientImpl implements Client, PropertiesAware, ConfigurationAware {
    String ip;
    int port;
    Log log;
    @Override
    public void send(Collection<Environment> collection) throws Exception {
        Socket socket = new Socket(ip, port);
        log.debug("客户端开始发送数据...");
//        Collection<Environment> envs = new GatherImpl().gather();
        OutputStream out = socket.getOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(out);
        oos.writeObject(collection);
        oos.flush();
        oos.close();
        out.close();
        socket.close();
    }

    @Override
    public void setConfiguration(Configuration configuration) throws Exception {
        log = configuration.getLogger();
    }

    @Override
    public void init(Properties properties) throws Exception {
        port = Integer.parseInt(properties.getProperty("client-port"));
        ip = properties.getProperty("client-host");
    }
}
