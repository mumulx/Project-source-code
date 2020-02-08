package com.yanqun.util;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

public class C3P0Demo {
    public static DataSource getDataSourceWithC3P0(){
        ComboPooledDataSource c3p0 = new ComboPooledDataSource();
        try {
            c3p0.setDriverClass("oracle.jdbc.driver.OracleDriver");
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        c3p0.setJdbcUrl("jdbc:oracle:thin:@127.0.0.1:1521:ORCL");
        c3p0.setUser("scott");
        c3p0.setPassword("tiger");

        return c3p0 ;
    }

    public static DataSource getDataSourceWithC3P0ByXml(){
        ComboPooledDataSource c3p0 = new ComboPooledDataSource("yanqun");
        return c3p0 ;
    }

    public static void main(String[] args)throws Exception {
        System.out.println(getDataSourceWithC3P0().getConnection());
        System.out.println(getDataSourceWithC3P0ByXml().getConnection());
    }
}
