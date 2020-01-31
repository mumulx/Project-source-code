package org.lanqiao.entity;

import java.util.List;

public class StudentClass {
	private int classId;
	private String className;
	
	
	//增加学生属性 (通过该字段 让Student类和StudentClass类建立起关联)
	List<Student> students ;
	
	
	public List<Student> getStudents() {
		return students;
	}
	public void setStudents(List<Student> students) {
		this.students = students;
	}
	public int getClassId() {
		return classId;
	}
	public void setClassId(int classId) {
		this.classId = classId;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	
}
