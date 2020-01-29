package org.lanqiao.dao.impl;

import org.apache.ibatis.session.SqlSession;
import org.lanqiao.entity.Student;
import org.lanqiao.mapper.StudentMapper;
import org.mybatis.spring.support.SqlSessionDaoSupport;

public class StudentDaoImpl extends SqlSessionDaoSupport implements StudentMapper {

	@Override
	public void addStudent(Student student) {
		SqlSession session = super.getSqlSession() ;
		StudentMapper stuDao = session.getMapper(StudentMapper.class) ;
		stuDao.addStudent(student);
	}
	
}
