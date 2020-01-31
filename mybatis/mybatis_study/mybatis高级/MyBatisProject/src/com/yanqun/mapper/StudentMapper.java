package com.yanqun.mapper;

import com.yanqun.entity.Student;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.HashMap;
import java.util.List;

//接口的编写 需要根据约定：（studentMapper.xml）
public interface StudentMapper {

    //按照“约定”编写的“根据学号，查询一个学生”的接口方法
    //@Select("select * from student where stuNo = #{stuNo}")
    Student queryStudentByNo (int stuNo);

    //按照“约定”编写的“查询全部学生”的接口方法
    public abstract List<Student> queryAllStudents ();

    //按照“约定”编写的“增加一个学生”的接口方法
    public abstract Integer addStudent(Student student);

    public abstract Integer addStudent(@Param("sNo") Integer stuNo, @Param("sName")String stuName, @Param("sAge")Integer stuAge, @Param("gName")String graName);

    public abstract Integer addStudent(@Param("sNo")Integer stuNo, @Param("stu")Student student);

    HashMap<String,Object> queryStudentOutByHashMap(int stuNo);

    @MapKey("STUNAME")
    HashMap<String,Student> queryStudentsByHashMap();



    //按照“约定”编写的“根据学号，删除一个学生”的接口方法
    public abstract void deleteStudentByNo(int stuNo);
    //按照“约定”编写的“根据学号，修改一个学生”的接口方法
    public abstract void updateStudentByNo(Student student);

    //stuNo  stuName  stuAge
    Student queryStudentByNoWithResultMap(int sno);


    List<Student> queryStudentsWithResultMap();

    List<Student> queryStudentByNoWithONGL(Student student);
    void addStudentOracle (List<Student> students) ;
    void addStudentMySql (List<Student> students) ;
    void addStudentMySql2 (List<Student> students) ;
    List<Student> queryStudents () ;

}
