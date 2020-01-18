package com.lx.test;

import com.lx.entity.Student1;
import com.lx.mapper.Student1Mapper;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

public class Test {
    public static void testQuery() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        Student1Mapper studentMapper = context.getBean("student1Mapper", Student1Mapper.class);
        List<Student1> students = studentMapper.selectAll();
        System.out.println(students);

    }
    public static void testQuery1() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        Student1Mapper studentMapper = context.getBean("student1Mapper", Student1Mapper.class);
        Example example = new Example(Student1.class);
        //升降序
        example.orderBy("stuName").desc().orderBy("stuNo").asc();
        //唯一   如果值重复  则取一个
        example.setDistinct(true);
        //指定 查询的字段
        example.selectProperties();
        //Mybatis 逆向工程    ：qbc
        // select... from student1 where (stu_no>1 and stu_no<10) or (stu_name like '%s%')
        //创建条件对象
        Example.Criteria c1 = example.createCriteria();
        Example.Criteria c2 = example.createCriteria();
        c1.andGreaterThanOrEqualTo("stuNo", 1);
        c1.andLessThanOrEqualTo("stoNo", 10);
        c2.andLike("stuName", "%s%");
        example.or(c2);

        List<Student1> students = studentMapper.selectByExample(example);
        System.out.println(students);

    }
    public static void main(String[] args) {
        testQuery();
    }
}
