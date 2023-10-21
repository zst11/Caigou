package com.briup.smart.env.impl;

import com.briup.smart.env.Configuration;
import com.briup.smart.env.client.Client;
import com.briup.smart.env.client.Gather;
import com.briup.smart.env.server.DBStore;
import com.briup.smart.env.server.Server;
import com.briup.smart.env.support.ConfigurationAware;
import com.briup.smart.env.support.PropertiesAware;
import com.briup.smart.env.util.Backup;
import com.briup.smart.env.util.Log;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class ConfigurationImpl implements Configuration {
    // 准本一个map集合用来返回对象
    HashMap<String,Object> map = new HashMap<>();
    // 准备一个单列
    private static final ConfigurationImpl configuration = new ConfigurationImpl();
    // properties对象
    private final Properties properties = new Properties();
    private ConfigurationImpl(){
        // 所有的主逻辑在构造器里洗
        // dom4j读取xml文件内容,读到全限类名
        SAXReader saxReader = new SAXReader();
        InputStream in = ConfigurationImpl.class.getClassLoader().getResourceAsStream("conf.xml");
        Document read = null;
        try {
            read = saxReader.read(in);
            Element rootElement = read.getRootElement();
            List<Element> elements = rootElement.elements();
            for (Element element : elements) {
                Attribute attribute = element.attribute("class");
                // 将每个标签的标签名和属性的权限类名放到map中
                Class<?> clazz = Class.forName(attribute.getValue());
                Object instance = clazz.getConstructor().newInstance();
                // 准备properties对象
                // 遍历第二级子节点的标签名字作为properties的key，标签值为value
                List<Element> elements1 = element.elements();
                for (Element element1 : elements1) {
                    properties.setProperty(element1.getName(),element1.getText());
                }
                // key 标签名 value 反射获取的对象
                map.put(element.getName(),instance);
            }
        } catch (DocumentException | ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        // 将map put完之后再foreach map，然后调用PropertiesAware，ConfigurationAware
        // 如果不map完就进行强转，可能会有的还没放进去就进行强转可能会出错
        // 强转为init 和 setConfiguration
        // 这里的强转判断是判断接口
        map.forEach((k,v)->{
            if (v instanceof PropertiesAware){
                PropertiesAware p = (PropertiesAware) v;
                try {
                    p.init(properties);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (v instanceof ConfigurationAware){
                ConfigurationAware c = (ConfigurationAware) v;
                try {
                    c.setConfiguration(this);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public static Configuration getInstance(){
        if (configuration!=null){
            return configuration;
        }else {
            return new ConfigurationImpl();
        }
    }
    @Override
    public Log getLogger() throws Exception {
        return (Log) map.get("logger");
    }

    @Override
    public Server getServer() throws Exception {
        return (Server) map.get("server");
    }

    @Override
    public Client getClient() throws Exception {
        return (Client) map.get("client");
    }

    @Override
    public DBStore getDbStore() throws Exception {
        return (DBStore) map.get("dbStore");
    }

    @Override
    public Gather getGather() throws Exception {
        return (Gather) map.get("gather");
    }

    @Override
    public Backup getBackup() throws Exception {
        return (Backup) map.get("backup");
    }
}
