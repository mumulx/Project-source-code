package org.lanqiao.mapper;

import java.util.List;

import org.lanqiao.entity.StudentClass;

//操作Mybatis的接口
public interface StudentClassMapper {
	//查询全部班级
	List<StudentClass> queryClassAndStudents();
}
