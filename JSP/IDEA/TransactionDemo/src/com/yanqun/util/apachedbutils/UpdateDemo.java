package com.yanqun.util.apachedbutils;

import com.yanqun.util.DataSourceUtil;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.SQLException;

public class UpdateDemo {

    public static void add() throws SQLException {
        QueryRunner runner =  new QueryRunner(DataSourceUtil.getDataSourceWithC3P0()) ;
        int count = runner.update( "insert into student(id,name) values(?,?)",new Object[]{4,"zl"} ) ;
        System.out.println(count);

    }
    public static void delete() throws SQLException {
        QueryRunner runner =  new QueryRunner(DataSourceUtil.getDataSourceWithC3P0()) ;
        int count = runner.update( "delete from student where id = ?",4 ) ;
        System.out.println(count);

    }

    public static void update() throws SQLException {
        QueryRunner runner =  new QueryRunner(DataSourceUtil.getDataSourceWithC3P0()) ;
        int count = runner.update( "update student set name = ? where id = ?",new Object[]{"xx",3} ) ;
        System.out.println(count);

    }

    public static void main(String[] args) throws SQLException {
//        add();
//        delete();
        update();
    }

}
