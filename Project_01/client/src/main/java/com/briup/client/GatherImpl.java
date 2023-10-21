package com.briup.client;

import com.briup.smart.env.Configuration;
import com.briup.smart.env.client.Gather;
import com.briup.smart.env.entity.Environment;
import com.briup.smart.env.impl.ConfigurationImpl;
import com.briup.smart.env.support.ConfigurationAware;
import com.briup.smart.env.support.PropertiesAware;
import com.briup.smart.env.util.Backup;
import com.briup.smart.env.util.Log;
import org.junit.Test;

import java.io.*;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Properties;


public class GatherImpl implements Gather, Serializable , PropertiesAware ,ConfigurationAware{
    String f1 = "";
    Log log ;
    // 备份文件路径
    private String backupFile;
    // 使用备份模块
    private Backup backup;
    @Override
    public void setConfiguration(Configuration configuration) throws Exception {
        log = configuration.getLogger();
        backup =configuration.getBackup();
    }

    @Override
    public void init(Properties properties) throws Exception {
        f1 =  properties.getProperty("gather-file-path");
        backupFile = properties.getProperty("gather-backup-file-path");
    }
    @Override
    public Collection<Environment> gather() throws Exception {
// 定义一个用来返回结果的集合
        Collection<Environment> list = new LinkedList<>();
        // 统计一下各个数据的条数
        // 温湿度
        int count1 = 0;
        // 光照
        int count2 = 0;
        // CO2
        int count3 = 0;
        // 1、读取文件？文件输入字符流、缓冲流
//        BufferedReader br = null;
//        InputStream in = GatherImpl.class.getClassLoader().getResourceAsStream(f1);
//        br = new BufferedReader(new InputStreamReader(in));
        RandomAccessFile raf = new RandomAccessFile("client/src/main/resources/"+f1, "rw");
        // plus:希望文件有更新的时候才更新，没有就不更新 -》备份模块的方法 备份的是文件读取到最后的位置（也就是long类型的指针）
        // 判断备份文件存在？
        File file = new File("client/src/" + backupFile);
        if (file.exists()){
            // 如果存在，则将当前文件和备份文件对比，是否更新？ 根据大小
            // 备份文件小于采集文件 -》 从新添加的位置开始读 RandomAccessFile
            Object load = backup.load(file.getAbsolutePath(), Backup.LOAD_UNREMOVE);
            Long l = 0L;
            if (load instanceof Long){
                l = (Long) load;
            }
            log.info("保存的长度"+l);
            log.info("本次需保存的长度"+raf.length());
            if (raf.length()>l){
                // 有更新，从保存的位置开始读取
                raf.seek(l);
                raf.write('\n');
            }else if (raf.length()<l){
                // 文件出错了
                log.warn("文件长度出错，比备份的内容要少");
                return null;
            }else {
                // 没变化
                log.warn("无更新");
                return null;
            }
        }
        String line = null;
        while((line = raf.readLine())!=null){
            //2、对数据进行一些操作
            // 将数据分割出来
            String[] split = line.split("[|]");
            String srcId = split[0];
            String desId = split[1];
            String devId = split[2];
            int count = Integer.parseInt(split[4]);
            String cmd = split[5];
            int status = Integer.parseInt(split[7]);
            long time = Long.parseLong(split[8]);
            // 根据第四部分的数据来区分环境种类
            String sensorAddress = split[3];
            Environment environment = new Environment();
            setEnv(srcId, desId, devId, count, cmd, status, time, sensorAddress, environment);
            if("16".equals(sensorAddress)){
                count1++;
                // 温湿度：需要拆分温度和湿度两个对象
                // 接收温湿度数据的字符串
                String tempData = split[6];
                environment.setName("温度");
                // 计算一下温度数据
                int v1 = Integer.parseInt(tempData.substring(0,4),16);
                // 使用公式得到data
                float data = (float)(v1 * 0.00268127F-46.85F);
                environment.setData(data);

                // 新创建一个对象（拷贝对象）
                Environment env = new Environment();
                env.setName("湿度");
                setEnv(srcId, desId, devId, count, cmd, status, time, sensorAddress, env);
                // 计算一下湿度数据
                int v2 = Integer.parseInt(tempData.substring(4,8),16);
                float data2 = (float)(v2*0.00190735F-6F);
                env.setData(data2);
                list.add(env);
            }else if("256".equals(sensorAddress)){
                count2++;
                // 光照强度
                environment.setName("光照强度");
                // 计算一下数据
                float data =  Integer.parseInt(split[6].substring(0,4),16);
                environment.setData(data);
            }else if("1280".equals(sensorAddress)) {
                count3++;
                // CO2浓度
                environment.setName("二氧化碳浓度");
                // 计算一下数据
                float data =  Integer.parseInt(split[6].substring(0,4),16);
                environment.setData(data);
            }
            list.add(environment);
        }
        // 采集完毕之后更新备份信息
        // 读取到最后的指针位置
        long filePointer = raf.getFilePointer();
        backup.store(file.getAbsolutePath(),filePointer,Backup.STORE_APPEND);
        // 输出
        log.debug("本次共采集环境数据【"+list.size()+"】条，其中：");
        log.debug("温度数据【"+count1+"】条，");
        log.debug("湿度数据【"+count1+"】条，");
        log.debug("光照强度数据【"+count2+"】条，");
        log.debug("CO2浓度数据【"+count3+"】条。");
        return list;
    }

    private void setEnv(String srcId, String desId, String devId, int count, String cmd, int status, long time, String sensorAddress, Environment env) {
        env.setSrcId(srcId);
        env.setDevId(devId);
        env.setCmd(cmd);
        env.setCount(count);
        env.setStatus(status);
        env.setDesId(desId);
        env.setSensorAddress(sensorAddress);
        env.setGatherDate(new Timestamp(time));
    }

    @Test
    public void test() throws Exception {
        Collection<Environment> list = ConfigurationImpl.getInstance().getGather().gather();
        // 客户端⾥⾯判断，如果list为空就不操作
        // 如果list不为空就send
        if(list!=null) {
            list.forEach(System.out::println);
        }
    }
}
