package org.lanqiao.mapper;

import org.lanqiao.entity.Student;

public interface StudentMapper {
	public void addStudent(Student student	);

	Student queryStudentByStuno(int stuno);
}
