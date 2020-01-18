package com.lx.test;


import com.lx.entity.Student;
import com.lx.mapper.StudentMapper;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class Test {

    public static void testQuery() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        StudentMapper studentMapper = context.getBean("studentMapper", StudentMapper.class);
        List<Student> students = studentMapper.selectAll();
        System.out.println(students);

    }

    public static void testInsert() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        StudentMapper studentMapper = context.getBean("studentMapper", StudentMapper.class);
        Student student = new Student(2, "aa", 22);
        //studentMapper.insert(student);// MP 可以将主键值回写到对象中      但是mapper默认不会  如果需要回写可以设置  @GeneratedValue   主键回写
        List<Student> students = studentMapper.selectAll();
        System.out.println(students);
    }

    //查询一个
    //当查询结果为多个的时候会报错
    public static void testQuery2() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        StudentMapper studentMapper = context.getBean("studentMapper", StudentMapper.class);
        Student student = new Student();
        student.setStuName("aa");
        student.setStuAge(22);
        Student student1 = studentMapper.selectOne(student);// MP : wrapper    ... where stuno =??
        System.out.println(student1);

    }

    //
    public static void testQuery3() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        StudentMapper studentMapper = context.getBean("studentMapper", StudentMapper.class);
        //selectByRowBounds   分页  假分页 是将数据库中的所有数据查询过来后  然后在寻出满足条件的指定条数据
        //studentMapper.selectByRowBounds();// MP : wrapper    ... where stuno =??
    }

    //
    public static void testInsert2() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        StudentMapper studentMapper = context.getBean("studentMapper", StudentMapper.class);
        Student student = new Student();
        student.setStuName("xx");
/*
* selective
INSERT INTO tb_student ( stu_no,stu_name ) VALUES( ?,? )

没有selectvie
INSERT INTO tb_student ( stu_no,stu_name,stu_age ) VALUES( ?,?,? )

selective：对于没有操作的值，不进行任何处理
没有selective：对于没有操作的值， 赋值为NULL
。可以发现，selective在insert操作 基本没有区别.
但对于修改，一般建议 加上selective


*
* */
        //studentMapper.insert(student);
        //studentMapper.insertSelective(student);
    }


    public static void testUpdate() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        StudentMapper studentMapper = context.getBean("studentMapper", StudentMapper.class);
        Student student = new Student();
        student.setStuName("aa");
        student.setStuNo(2);
        // 本来的  age字段的值为22 但是因为  字段没有赋值  所以默认赋值为null   结果更新后的age值为 null
        //22---》null
        //studentMapper.updateByPrimaryKey(student);
        //本来的  age字段的值为22 但是因为  字段没有赋值  所以默认不进行修改   结果更新后的age值为 22  保留了原先的字段值
        //22--->22
       // studentMapper.updateByPrimaryKeySelective(student);

    }
    // 自定义自己的查询
    public static void testMyQuery() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        StudentMapper studentMapper = context.getBean("studentMapper", StudentMapper.class);
        Student student = new Student();
        student.setStuName("aa");
        student.setStuAge(22);
        Student student1 = studentMapper.mySelectOne(student);// MP : wrapper    ... where stuno =??
        System.out.println(student1);

    }

    public static void main(String[] args) throws Exception {

//        testQuery();
//        testInsert();
//        testQuery2();
        testMyQuery();

    }
}
