package com.briup.server;

import com.briup.smart.env.Configuration;
import com.briup.smart.env.entity.Environment;
import com.briup.smart.env.server.DBStore;
import com.briup.smart.env.server.Server;
import com.briup.smart.env.support.ConfigurationAware;
import com.briup.smart.env.support.PropertiesAware;
import com.briup.smart.env.util.Log;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Properties;

public class ServerImpl implements Server, ConfigurationAware, PropertiesAware {
    private boolean stop;
    Log log;
    int port;
    DBStore dbStore;
    @Override
    public void receive() throws Exception {
        ServerSocket serverSocket = new ServerSocket(port);
        while (!stop){
            Socket socket = serverSocket.accept();
            log.debug("客户端连接成功"+socket);
            Thread thread = new Thread(()->{
                InputStream ins = null;
                ObjectInputStream ois = null;
                try {
                    ins = socket.getInputStream();
                    ois = new ObjectInputStream(ins);
                    Object object = ois.readObject();
                    Collection<Environment> list = new ArrayList<>(); // LinkedList ?
                    if (object instanceof Collection<?>){
                        Collection<?> l = (Collection<?>) object;
                        for (Object o:l){
                            if (o instanceof Environment){
                                list.add((Environment) o);
                            }
                        }
                    }
                    System.out.println(list.size());

                    // 入库
                    dbStore.saveDB(list);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        assert ins != null;
                        ins.close();
                        assert ois != null;
                        ois.close();
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            thread.start();
        }
    }

    @Override
    public void shutdown() throws Exception {
        stop = true;
        System.exit(0);
    }

    @Override
    public void setConfiguration(Configuration configuration) throws Exception {
        log = configuration.getLogger();
        dbStore = configuration.getDbStore();
    }

    @Override
    public void init(Properties properties) throws Exception {
        port = Integer.parseInt(properties.getProperty("server-port"));
    }
}
