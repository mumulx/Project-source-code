package com.yanqun.com.yanqun.apachedbutils;

import com.yanqun.entity.Student;
import com.yanqun.util.DataSourceUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.*;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class Test {

    //select *from student ;  -> List<Student>
    //查询单行数据
    public static void testArrayHandler() throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceUtil.getDataSourceWithC3P0ByXml());//自动提交事务
        Object[] student = runner.query("select * from student where id > ? " ,new ArrayHandler(),1) ;
        System.out.println( student[0]+","+student[1]);
    }

    //查询单多数据
    public static void testArrayListHandler() throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceUtil.getDataSourceWithC3P0ByXml());//自动提交事务
        List<Object[]> students = runner.query("select * from student where id > ? " ,new ArrayListHandler(),1) ;
        for(Object[] student:students){
            System.out.println( student[0]+","+student[1]);
        }

    }

    //查询单行数据(放入對象中)
    public static void testBeanHandler() throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceUtil.getDataSourceWithC3P0ByXml());//自动提交事务
        Student student = runner.query("select * from student where id > ? " ,new BeanHandler<Student>(Student.class),1) ;
        System.out.println( student.getId()+","+student.getName());
    }

    //查询多行数据(放入對象中)
    public static void testBeanListHandler() throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceUtil.getDataSourceWithC3P0ByXml());//自动提交事务
        List<Student> students = runner.query("select * from student where id > ? " ,new BeanListHandler<Student>(Student.class),1) ;
        for(Student student:students){
            System.out.println( student.getId()+","+student.getName());
        }
    }

    //查询多行数据(放入map中)
    public static void testBeanMapHandler() throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceUtil.getDataSourceWithC3P0ByXml());//自动提交事务     --   java中对应oracle默認的數值類型  BigDecimal
        Map<BigDecimal,Student> students = runner.query("select * from student where id > ? " ,new BeanMapHandler<BigDecimal,Student>( Student.class,"id" ),1) ;
        // 2: ls,  3:ww
        Student stu = students.get(new BigDecimal(2)) ;

        System.out.println(stu.getId()+","+stu.getName());


    }



    //查询單行数据 map
    public static void testMapHandler() throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceUtil.getDataSourceWithC3P0ByXml());//自动提交事务
        Map<String,Object> student = runner.query("select * from student where id > ? " ,new MapHandler(),1) ;
        System.out.println( student);
    }

    //查询單值数据
    public static void testScalarHandler() throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceUtil.getDataSourceWithC3P0ByXml());//自动提交事务
//        BigDecimal result = runner.query("select name from student where id = ? " ,new ScalarHandler<BigDecimal>(),2) ;
        String result = runner.query("select name from student where id = ? " ,new ScalarHandler<String>(),2) ;
        System.out.println( result);
    }

    //查询多行数据 map
    public static void testMapListHandler() throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceUtil.getDataSourceWithC3P0ByXml());//自动提交事务
        List<Map<String,Object>> students = runner.query("select * from student where id > ? " ,new MapListHandler(),1) ;
        System.out.println( students);
    }

    //查询多行数据 keyed
    public static void testKeyedHandler() throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceUtil.getDataSourceWithC3P0ByXml());//自动提交事务
        Map<String,Map<String,Object>> students = runner.query("select * from student where id > ? " ,new KeyedHandler<String>("name"),1) ;
        System.out.println( students);
    }

    //查询多行数据中的某一列
    public static void testColumnListHandler() throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceUtil.getDataSourceWithC3P0ByXml());//自动提交事务
        List<String> names = runner.query("select * from student where id > ? " ,new ColumnListHandler<String>("name"),1) ;
        System.out.println( names);
    }
    public static void main(String[] args) throws SQLException {
//        testArrayHandler();
//        testArrayListHandler();
//        testBeanHandler();
//        testBeanListHandler();
//        testBeanMapHandler();
//        testMapHandler();


//        testMapListHandler();
//        testKeyedHandler();
//        testColumnListHandler();
        testScalarHandler();
    }


}
