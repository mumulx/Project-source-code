package com.yanqun.test;

import com.yanqun.entity.Student;
import com.yanqun.entity.StudentExample;
import com.yanqun.mapper.StudentMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

public class TestGeneratorDemo {

    public static void main(String[] args) throws IOException {
        String resource = "conf.xml";
        Reader reader = Resources.getResourceAsReader(resource);
        SqlSessionFactory sessionFactory
                = new SqlSessionFactoryBuilder().build(reader);
        SqlSession session = sessionFactory.openSession();

        StudentMapper mapper = session.getMapper(StudentMapper.class);
        //Example中的Criteria：查询条件
       // List<Student> students = mapper.selectByExample(null) ;

        //规则：example 默认使用的是 第一个criteria
        StudentExample example = new StudentExample() ;
        StudentExample.Criteria criteria = example.createCriteria();
      //  criteria.andStunoBetween((short) 32, (short) 33);// stuno: 2-3
        criteria.andStunameLike("%l%");
        //where  (xx=xx  and xx =x)  or  (xx =xxx  and  xx =xx) ;

        //where   stuname like '%z%'   or  ( stuno <=31   and granameLike "%j%) ;
        //criteria:where   stuname like '%z%'
         //   or
        //criteria:   stuno <=31   and granameLike "%j% ；
        StudentExample.Criteria criteria1 = example.createCriteria();
        criteria1.andStunoLessThanOrEqualTo((short)31) ; //<=
        criteria1.andGranameLike("%j%") ;
        example.or(criteria1) ;
        //query by Criteria   ,  QBC
        List<Student> students = mapper.selectByExample(example ) ;
        System.out.println(students );
        session.close();

    }
}
