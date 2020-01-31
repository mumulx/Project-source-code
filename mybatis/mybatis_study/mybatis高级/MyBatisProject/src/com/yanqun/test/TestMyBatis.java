package com.yanqun.test;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.xml.internal.bind.v2.runtime.output.FastInfosetStreamWriterOutput;
import com.yanqun.entity.Student;
import com.yanqun.mapper.StudentMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TestMyBatis {
    // 增加
    public static void addBatchStudents() throws IOException {
        String resource = "conf.xml";
        Reader reader = Resources.getResourceAsReader(resource);
        SqlSessionFactory sessionFactory
                = new SqlSessionFactoryBuilder().build(reader);
        SqlSession session = sessionFactory.openSession( );

        StudentMapper studentMapper = session.getMapper(StudentMapper.class);//接口

        long start = System.currentTimeMillis();
        for(int i=0;i<100000;i++) {
            Student stu = new Student((int)(Math.random()*9000) +1000, "abc", 44, "xx");
            studentMapper.addStudent(stu);
        }
        long end = System.currentTimeMillis();
        System.out.println(end-start);
        session.commit();//手工提交
        session.close();
    }
  // 增加
    public static void addStudentOracle() throws IOException {
        String resource = "conf.xml";
        Reader reader = Resources.getResourceAsReader(resource);
        SqlSessionFactory sessionFactory
                = new SqlSessionFactoryBuilder().build(reader);
        SqlSession session = sessionFactory.openSession( );

        StudentMapper studentMapper = session.getMapper(StudentMapper.class);//接口

        long start = System.currentTimeMillis();

        List<Student> students = new ArrayList<>();
        students.add( new Student(10,"zs") );
        students.add( new Student(20,"ls") );
        studentMapper.addStudentOracle(students);


        long end = System.currentTimeMillis();
        System.out.println(end-start);
        session.commit();//手工提交
        session.close();
    }// 增加
    public static void addStudentMysql() throws IOException {
        String resource = "conf.xml";
        Reader reader = Resources.getResourceAsReader(resource);
        SqlSessionFactory sessionFactory
                = new SqlSessionFactoryBuilder().build(reader);
        SqlSession session = sessionFactory.openSession( );

        StudentMapper studentMapper = session.getMapper(StudentMapper.class);//接口

        long start = System.currentTimeMillis();

        List<Student> students = new ArrayList<>();
        students.add( new Student(510,"311zs") );
        students.add( new Student(610,"311ls") );
        studentMapper.addStudentMySql2(students);


        long end = System.currentTimeMillis();
        System.out.println(end-start);
        session.commit();//手工提交
        session.close();
    }

    // 增加
    public static void addStudent() throws IOException {
        String resource = "conf.xml";
        Reader reader = Resources.getResourceAsReader(resource);
        SqlSessionFactory sessionFactory
                = new SqlSessionFactoryBuilder().build(reader);
        SqlSession session = sessionFactory.openSession();

        StudentMapper studentMapper = session.getMapper(StudentMapper.class);//接口

        Student stu = new Student(555, null, 44, "xx");
        studentMapper.addStudent( stu);

        session.commit();//手工提交
        session.close();
    }

    // 刪除
    public static void deleteStudent() throws IOException {
        String resource = "conf.xml";
        Reader reader = Resources.getResourceAsReader(resource);
        SqlSessionFactory sessionFactory
                = new SqlSessionFactoryBuilder().build(reader);
        SqlSession session = sessionFactory.openSession();


        // 传入StudentMapper接口，返回该接口的mapper代理对象studentMapper
        StudentMapper studentMapper = session.getMapper(StudentMapper.class);//接口

        //通过mapper代理对象studentMapper，来调用IStudentMapper接口中的方法
        studentMapper.deleteStudentByNo(4);

        session.commit();
        session.close();
    }

    // 修改
    public static void updateStudent() throws IOException {
        String resource = "conf.xml";
        Reader reader = Resources.getResourceAsReader(resource);
        SqlSessionFactory sessionFactory
                = new SqlSessionFactoryBuilder().build(reader);
        SqlSession session = sessionFactory.openSession();


        // 传入StudentMapper接口，返回该接口的mapper代理对象studentMapper
        StudentMapper studentMapper = session.getMapper(StudentMapper.class);//接口
        Student stu = new Student(3, "wxw", 55, "yy");
        studentMapper.updateStudentByNo(stu);

        session.commit();
        session.close();
    }

    // 根据学号查询一个学生
    public static void testQueryByNo() throws IOException {
        String resource = "conf.xml";
        Reader reader = Resources.getResourceAsReader(resource);

        SqlSessionFactory sessionFactory
                = new SqlSessionFactoryBuilder().build(reader);


        SqlSession session = sessionFactory.openSession();

        //传入StudentMapper接口，返回该接口的mapper代理对象studentMapper
        StudentMapper studentMapper = session.getMapper(StudentMapper.class);//接口

        //通过mapper代理对象studentMapper，来调用IStudentMapper接口中的方法
        Student student = studentMapper.queryStudentByNo(11);

        System.out.println(student+"****");
        session.close();
    }

    // 根据学号查询一个学生
    public static void queryStudentOutByHashMap() throws IOException {
        String resource = "conf.xml";
        Reader reader = Resources.getResourceAsReader(resource);
        SqlSessionFactory sessionFactory
                = new SqlSessionFactoryBuilder().build(reader);
        SqlSession session = sessionFactory.openSession();

        //传入StudentMapper接口，返回该接口的mapper代理对象studentMapper
        StudentMapper studentMapper = session.getMapper(StudentMapper.class);//接口

        HashMap<String, Object> studentMap
                = studentMapper.queryStudentOutByHashMap(11);

//        System.out.println( studentMap.get("no") +"," +studentMap.get("name") +"," +studentMap.get("age")  );
        System.out.println(studentMap);
                session.close();
    }



    public static void queryStudentByNoWithResultMap() throws IOException {
        String resource = "conf.xml";
        Reader reader = Resources.getResourceAsReader(resource);
        SqlSessionFactory sessionFactory
                = new SqlSessionFactoryBuilder().build(reader);
        SqlSession session = sessionFactory.openSession();

        //传入StudentMapper接口，返回该接口的mapper代理对象studentMapper
        StudentMapper studentMapper = session.getMapper(StudentMapper.class);//接口

        Student student
                = studentMapper.queryStudentByNoWithResultMap(11);
        System.out.println(student);
                session.close();
    }

    public static void queryStudentsByHashMap() throws IOException {
        String resource = "conf.xml";
        Reader reader = Resources.getResourceAsReader(resource);
        SqlSessionFactory sessionFactory
                = new SqlSessionFactoryBuilder().build(reader);
        SqlSession session = sessionFactory.openSession();

        //传入StudentMapper接口，返回该接口的mapper代理对象studentMapper
        StudentMapper studentMapper = session.getMapper(StudentMapper.class);//接口

        HashMap<String, Student> studentMap
                = studentMapper.queryStudentsByHashMap();

        System.out.println( studentMap.get("no") +"," +studentMap.get("name") +"," +studentMap.get("age")  );
                session.close();
    }



    public static void queryStudents() throws IOException {
        String resource = "conf.xml";
        Reader reader = Resources.getResourceAsReader(resource);
        SqlSessionFactory sessionFactory
                = new SqlSessionFactoryBuilder().build(reader);
        SqlSession session = sessionFactory.openSession();

        //传入StudentMapper接口，返回该接口的mapper代理对象studentMapper
        StudentMapper studentMapper = session.getMapper(StudentMapper.class);//接口
        //加入分页功能
//        Page<Object> page = PageHelper.startPage(2, 3);
       PageHelper.startPage(2, 3);

            //lambda形式
//        Page<Student> page = PageHelper.startPage(2, 3).doSelectPage(()-> studentMapper.queryStudents());
//        List<Student> list = page.getResult();




        // select * from student order by stuno  ->拦截器
        //select * from student order by stuno limit 6,3
        List<Student> list = studentMapper.queryStudents() ;
       //    List<Student> students =  studentMapper.queryStudents();

      //  System.out.println( list  );
        for(Student student :list){
            System.out.println(student);
        }

        PageInfo<Student> pageInfo = new PageInfo<>(list);
        System.out.println("当前页："+pageInfo.getPageNum());
        System.out.println("总数据量："+pageInfo.getTotal());
        System.out.println("总页码：" +pageInfo.getPages());
        System.out.println("页面大小：" +pageInfo.getPageSize());
        System.out.println("最开头那一页："+ pageInfo.getNavigateFirstPage());
        System.out.println("每一页的页号");

        for( int pageNum : pageInfo.getNavigatepageNums()){
            System.out.println(pageNum);
        }


        session.close();
    }
    public static void queryStudentsWithResultMap() throws IOException {
        String resource = "conf.xml";
        Reader reader = Resources.getResourceAsReader(resource);
        SqlSessionFactory sessionFactory
                = new SqlSessionFactoryBuilder().build(reader);
        SqlSession session = sessionFactory.openSession();

        //传入StudentMapper接口，返回该接口的mapper代理对象studentMapper
        StudentMapper studentMapper = session.getMapper(StudentMapper.class);//接口

        List<Student> students
                = studentMapper.queryStudentsWithResultMap();
        System.out.println(students);
        session.close();
    }

    public static void queryStudentByNoWithONGL() throws IOException {
        String resource = "conf.xml";
        Reader reader = Resources.getResourceAsReader(resource);
        SqlSessionFactory sessionFactory
                = new SqlSessionFactoryBuilder().build(reader);
        SqlSession session = sessionFactory.openSession();

        //传入StudentMapper接口，返回该接口的mapper代理对象studentMapper
        StudentMapper studentMapper = session.getMapper(StudentMapper.class);//接口
//        select * from student where stuname like '%s%' and stuage = 23 and graname like '%b%' ;
            Student student = new Student() ;
//        student.setStuAge(23);

        student.setStuName("s");
        List<Student> students
                = studentMapper.queryStudentByNoWithONGL(student);
        System.out.println(students);
        session.close();
    }

    public static void main(String[] args) throws IOException {
//      testQueryByNo();
//        addStudent() ;
//        addBatchStudents();
//      deleteStudent();
//        addStudentOracle();
//        addStudentMysql();
//        queryStudentsByHashMap();
//        queryStudentOutByHashMap();
//      updateStudent();
//        queryStudentsByHashMap();
//        queryStudentByNoWithResultMap();
//        queryStudentsWithResultMap();
//        queryStudentByNoWithONGL();
        queryStudents();
    }
}
