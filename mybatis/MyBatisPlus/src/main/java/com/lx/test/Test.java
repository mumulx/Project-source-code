package com.lx.test;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lx.entity.Student;
import com.lx.mapper.StudentMapper;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test {
    public static void testInsert() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        //从ioc容器中获取 xXXMapper对象
//        StudentMapper studentMapper = (StudentMapper)context.getBean("studentMapper");
        StudentMapper studentMapper = context.getBean("studentMapper", StudentMapper.class);
        Student student = new Student("zs", 23);
        int count = studentMapper.insert(student);
        System.out.println(count);
        //student插入后会将主键回写到student对象
        System.out.println(student);
    }

    public static void testdelete() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        //从ioc容器中获取 xXXMapper对象
//        StudentMapper studentMapper = (StudentMapper)context.getBean("studentMapper");
        StudentMapper studentMapper = context.getBean("studentMapper", StudentMapper.class);
        int count = studentMapper.deleteById(1);
        System.out.println(count);
    }

    public static void testBachDelete() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        //从ioc容器中获取 xXXMapper对象
//        StudentMapper studentMapper = (StudentMapper)context.getBean("studentMapper");
        StudentMapper studentMapper = context.getBean("studentMapper", StudentMapper.class);
        List<Integer> stuNos = new ArrayList<Integer>();
        stuNos.add(2);
        stuNos.add(3);
        int count = studentMapper.deleteBatchIds(stuNos);
        System.out.println(count);
    }

    public static void testMapDelete() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        //从ioc容器中获取 xXXMapper对象
//        StudentMapper studentMapper = (StudentMapper)context.getBean("studentMapper");
        StudentMapper studentMapper = context.getBean("studentMapper", StudentMapper.class);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("stu_no", 4);//多个值的话是and的关系
        int count = studentMapper.deleteByMap(map);
        System.out.println(count);
    }

    public static void testUpdate() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        //从ioc容器中获取 xXXMapper对象
//        StudentMapper studentMapper = (StudentMapper)context.getBean("studentMapper");
        StudentMapper studentMapper = context.getBean("studentMapper", StudentMapper.class);
        Student student = new Student(5, "赵六", 12);

        int count = studentMapper.updateById(student);
        System.out.println(count);
    }

    public static void testUpdate2() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        //从ioc容器中获取 xXXMapper对象
//        StudentMapper studentMapper = (StudentMapper)context.getBean("studentMapper");
        StudentMapper studentMapper = context.getBean("studentMapper", StudentMapper.class);
        Student student = new Student(5, "王五", 12);
        // UPDATE student SET stu_name=?, stu_age=? WHERE (stu_no = ?)
        UpdateWrapper<Student> wrapper = new UpdateWrapper<>();
        wrapper.eq("stu_no", 3);
        int count = studentMapper.update(student, wrapper);
        System.out.println(count);
    }

    public static void testQuery() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        //从ioc容器中获取 xXXMapper对象
//        StudentMapper studentMapper = (StudentMapper)context.getBean("studentMapper");
        StudentMapper studentMapper = context.getBean("studentMapper", StudentMapper.class);

        Student student = studentMapper.selectById(5);
        System.out.println(student);
    }

    public static void testQueryBatch() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        //从ioc容器中获取 xXXMapper对象
//        StudentMapper studentMapper = (StudentMapper)context.getBean("studentMapper");
        StudentMapper studentMapper = context.getBean("studentMapper", StudentMapper.class);
        List<Integer> stuNos = new ArrayList<Integer>();
        stuNos.add(2);
        stuNos.add(3);
        stuNos.add(4);
        List<Student> students = studentMapper.selectBatchIds(stuNos);
        System.out.println(students);
    }

    public static void testQueryMap() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        //从ioc容器中获取 xXXMapper对象
//        StudentMapper studentMapper = (StudentMapper)context.getBean("studentMapper");
        StudentMapper studentMapper = context.getBean("studentMapper", StudentMapper.class);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("stu_no", 4);//多个值的话是and的关系);
        List<Student> students = studentMapper.selectByMap(map);
        System.out.println(students);
    }

    //条件查询
    public static void testQuery2() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        //从ioc容器中获取 xXXMapper对象
//        StudentMapper studentMapper = (StudentMapper)context.getBean("studentMapper");
        StudentMapper studentMapper = context.getBean("studentMapper", StudentMapper.class);
        QueryWrapper<Student> wrapper = new QueryWrapper<Student>();
        // where stuno between 3 and 5 and stu_age >=10
        wrapper.between("stu_no", 3, 5).ge("stu_age", 10);
        // where stuno between 3 and 5 or stu_age >=10
       /* wrapper.between("stu_no", 3, 5)
                .or()
                .ge("stu_age", 10);*/
        // or 加括号
/*        wrapper.between("stu_no", 3, 5)//属于labda  知识   jdk1.8 新增的
                .or(i->i.ge("stu_age", 10).le("stu_age",100))
                ;*/
        List<Student> students = studentMapper.selectList(wrapper);

        System.out.println(students);
    }

    public static void testAR() {
        //ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        //StudentMapper studentMapper = context.getBean("studentMapper", StudentMapper.class);
        // 必须在IOC容器中进行AR
        new ClassPathXmlApplicationContext("applicationContext.xml");
        Student student = new Student("哈哈", 22);
        student.insert();
    }

    public static void testAR1() {
        //ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        //StudentMapper studentMapper = context.getBean("studentMapper", StudentMapper.class);
        // 必须在IOC容器中进行AR
        new ClassPathXmlApplicationContext("applicationContext.xml");
        //Student student = new Student();
        /*student.setStuNo(2);
        student.deleteById();*/
        //student.deleteById(11);


        //Student student = new Student(5,"zs",90);
        //student.updateById();


        Student student = new Student();
        QueryWrapper<Student> wrapper = new QueryWrapper<>();
//        面向sql     查询的对象是表的字段名（列名）
//        wrapper.like("stu_name", "a");
//        面向对象    查询的对象是类的属性
        wrapper.lambda().like(Student::getStuName, "a");
        List<Student> students = student.selectList(wrapper);
        System.out.println(students);


    }
//    分页插件

    public static void testPage() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        StudentMapper studentMapper = context.getBean("studentMapper", StudentMapper.class);
        Page<Student> page = new Page<>(2, 2);
        //select * from student ; 第二个参数来存放where条件
        IPage<Student> pages = studentMapper.selectPage(page, null);
        System.out.println("当前页得数据++++" + page.getRecords());
        System.out.println("当前页页码++++" + page.getCurrent());
        System.out.println("总数据量++++" + page.getTotal());
        System.out.println("每一页得数据量++++" + page.getSize());

    }

    public static void testdeleteAll() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        StudentMapper studentMapper = context.getBean("studentMapper", StudentMapper.class);
        int delete = studentMapper.delete(null);
        System.out.println(delete);

    }

    //乐观锁
    public static void testleguan() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        StudentMapper studentMapper = context.getBean("studentMapper", StudentMapper.class);
        Student stuedent = new Student();
        stuedent.setStuName("sff");
        stuedent.setVersion(1);
        stuedent.setStuNo(2);
        int delete = studentMapper.updateById(stuedent);
        if(delete>0){
            System.out.println("修改成功");
        }else{
            System.out.println("修改失败");

        }

    }
    // 自己的sql语句
    public static void testdeleteByMyInjector() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        StudentMapper studentMapper = context.getBean("studentMapper", StudentMapper.class);
        studentMapper.deleteAllStudents();

    }
//逻辑删除

    public static void testdeleteByMyInjector1() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        StudentMapper studentMapper = context.getBean("studentMapper", StudentMapper.class);
        studentMapper.delete(null);

    }
    // 自动填充
    public static void testInsertByZDTC() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        StudentMapper studentMapper = context.getBean("studentMapper", StudentMapper.class);
        Student student = new Student();

        student.setStuAge(33);
        student.setVersion(1);
        studentMapper.insert(student);

    }



    public static void main(String[] args) throws Exception {
//        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
//
//        ComboPooledDataSource ds = (ComboPooledDataSource) context.getBean("dataSource");
//        System.out.println("ds-------"+ds);
//
//        Connection connection = ds.getConnection();
//        System.out.println("connention------"+connection);
        //testInsert();
        //testdelete();
        //testBachDelete();
        //testMapDelete();
        //testUpdate();
       testQuery();
//        testQueryBatch();
//        testQueryMap();
//          testQuery2();
//        testUpdate2();
//         testAR();
//        testAR1();
//        testPage();
        //testdeleteAll();
//        testdeleteByMyInjector();
//        testdeleteByMyInjector1();
        //testInsertByZDTC();







    }
}
