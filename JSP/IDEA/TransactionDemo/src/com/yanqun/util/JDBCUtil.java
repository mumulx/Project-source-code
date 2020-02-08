package com.yanqun.util;

import java.sql.Connection;
import java.sql.SQLException;

public class JDBCUtil {
    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<>() ;

    public static Connection getConnection() throws SQLException {
        Connection conn = threadLocal.get();//获取tl中的变量，如果不够，会自动创建副本
        if(conn == null){
            conn = DataSourceUtil.getDataSourceWithC3P0().getConnection();
            threadLocal.set(conn);
        }
        return conn;
    }

    //开启事务
    public static void beginTransaction() throws SQLException {
       //连接
        Connection conn =  getConnection() ;
        conn.setAutoCommit(false);//开启事务

    }

    //正常。提交事务
    public static void commitTransaction() throws SQLException {
        //连接
        Connection conn =  getConnection() ;
        //所有的 对象.方法()  全部排空
        if(conn !=null)
            conn.commit();

    }

    //失败。回滚事务
    public static void rollbackTransaction() throws SQLException {
        //连接
        Connection conn =  getConnection() ;
        if(conn !=null)
            conn.rollback();
    }

    public static void close() throws SQLException{
        Connection conn =  getConnection() ;
        if(conn !=null)
           conn.close();
        threadLocal.remove();
        conn = null ;
    }
}
