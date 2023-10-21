package com.briup.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class JDBCUtil {
    private static final DataSource dataSource;
    public static final String CLASS_NAME;
    public static final String URL;
    public static final String USERNAME;
    public static final String PASSWORD;
    //    定义本类三个对象，将自己用到得资源关闭
    private static Connection conn;
    private static Statement st;
    private static ResultSet rs;
    static {
        InputStream in = JDBCUtil.class.getClassLoader().getResourceAsStream("druid.properties");
        Properties properties = new Properties();
        try {
            properties.load(in);
            dataSource = DruidDataSourceFactory.createDataSource(properties);
            CLASS_NAME = properties.getProperty("driverClassName");
            URL = properties.getProperty("url");
            USERNAME = properties.getProperty("username");
            PASSWORD = properties.getProperty("password");
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
    //    工具类中得方法一般是静态方法


//    1、不使用连接池子获得连接对象
    public static Connection getConnection() throws Exception{
        Class.forName(CLASS_NAME);
        conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);
        return conn;
    }
    //    2、使用连接池获得对象
    public static Connection getDriudConnection() throws Exception{
        conn = dataSource.getConnection();
        return conn;
    }
    //    3、执行DDL语句的方法（st）
    public static int executeDDL(String sql) throws Exception{
//        内部实现用池子
        st = getDriudConnection().createStatement();
        return st.executeUpdate(sql);
    }
    //    4、执行DML语句的方法（st）
    public static int executeDML(String sql) throws Exception{
        return executeDDL(sql);
    }
    //    5、执行DQL语句的方法（st）
    public static ResultSet executeDQL(String sql) throws Exception{
        st = getDriudConnection().createStatement();
        return st.executeQuery(sql);
    }
    //    6、关闭的方法
    public static void close(){
        close(conn,st,rs);
    }
    public static void close(Connection conn, Statement st, ResultSet rs){
        if (rs!=null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (st!=null) {
            try {
                st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn!=null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
