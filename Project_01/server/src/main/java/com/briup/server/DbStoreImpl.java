package com.briup.server;

import com.briup.smart.env.Configuration;
import com.briup.smart.env.entity.Environment;
import com.briup.smart.env.server.DBStore;
import com.briup.smart.env.support.ConfigurationAware;
import com.briup.smart.env.support.PropertiesAware;
import com.briup.smart.env.util.Log;
import com.briup.smart.env.util.LogImpl;
import com.briup.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Calendar;
import java.util.Collection;
import java.util.Properties;

public class DbStoreImpl implements DBStore , PropertiesAware, ConfigurationAware {

    Log log;
    int batchSize;
    @Override
    public void saveDB(Collection<Environment> collection) throws Exception {
        Connection conn = JDBCUtil.getDriudConnection();
        int preDay = -1;
        PreparedStatement pst = null;
        // 定义统计个数的变量
        int count = 0;
        boolean flag = false;
        // 遍历集合
        for (Environment e : collection) {
            // 根据天数插入不同的表
            // Calender 或者 java.time包
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(e.getGatherDate());
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            String sql = "insert into env_detail_"+day+" values(?,?,?,?,?,?,?,?,?,?)";
            // pst 不能创建太多，所以需要优化，判断天改变没有，变了再创建pst
            // pst 的处理
            if (preDay!=day){
                if (pst!=null){
                    // 当天数发生变化，pst中可能还有没提交得数据
                    // 在这里直接提交
                    pst.executeBatch();
                }
                pst = conn.prepareStatement(sql);
                preDay = day;
            }
            // 批处理
            pst.setString(1,e.getName());
            pst.setString(2,e.getSrcId());
            pst.setString(3,e.getDesId());
            pst.setString(4,e.getDevId());
            pst.setString(5,e.getSensorAddress());
            pst.setInt(6,e.getCount());
            pst.setString(7,e.getCmd());
            pst.setInt(8,e.getStatus());
            pst.setFloat(9,e.getData());
            pst.setTimestamp(10,e.getGatherDate());
            pst.addBatch();
            count++;
            if (count%batchSize==0){
                pst.executeBatch();
                pst.clearBatch();
            }
        }
        if (pst!=null && count % 2000 != 0){
            pst.executeBatch();
            pst.clearBatch();
        }
        log.debug("入库成功");
        if (pst!=null){
            pst.close();
        }
        JDBCUtil.close(conn,null,null);
    }

    @Override
    public void setConfiguration(Configuration configuration) throws Exception {
        log = configuration.getLogger();
    }

    @Override
    public void init(Properties properties) throws Exception {
        batchSize = Integer.parseInt(properties.getProperty("batch-size"));
    }
}
