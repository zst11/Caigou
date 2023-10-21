package com.briup.smart.env.util;

import com.briup.smart.env.Configuration;
import com.briup.smart.env.support.ConfigurationAware;
import com.briup.smart.env.support.PropertiesAware;

import java.io.*;
import java.util.Properties;

public class BackupImpl implements Backup, ConfigurationAware, PropertiesAware {
    Log log;
    File file;
    @Override
    public Object load(String filePath, boolean del) throws Exception {
        checkFile(filePath,"r");
        FileInputStream in = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(in);
        Object o = ois.readObject();
        ois.close();
        in.close();
        if (del && file.exists()){
            if (file.delete()){
                log.info("备份文件已删除");
            }else {
                log.warn("删除失败，请检查");
            }
        }
        return o;
    }

    @Override
    public void store(String filePath, Object obj, boolean append) throws Exception {
        checkFile(filePath,"");
        FileOutputStream out = new FileOutputStream(file, append);
        ObjectOutputStream oos = new ObjectOutputStream(out);
        oos.writeObject(obj);
        oos.flush();
        oos.close();
        out.close();
        log.info(file.getName() +
                " 备份成功，位置为"+file.getAbsoluteFile());
    }

    private void checkFile(String filePath,String mode) throws Exception {
        if (filePath==null|| filePath.length() == 0){
            log.error("文件路径不能为空");
            throw new Exception("文件路径不能为空"); // 抛异常是可以让程序停止
        }
        file = new File(filePath);
        if (!file.exists()){
            log.error("文件不存在");
            if ("r".equals(mode)) {
                throw new Exception("文件备份不存在");
            }
        }
    }
    @Override
    public void setConfiguration(Configuration configuration) throws Exception {
        log = configuration.getLogger();
    }

    @Override
    public void init(Properties properties) throws Exception {

    }
}
