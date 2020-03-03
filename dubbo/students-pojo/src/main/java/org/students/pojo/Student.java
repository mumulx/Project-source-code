package org.students.pojo;

import java.io.Serializable;
//如果一个对象 需要 从内存存到硬盘，或者需要网络传输 ，则必须序列化
public class Student  implements Serializable{
	private int stuNo;
	private String stuName ;
	private int stuAge ;
	
	public Student() {
	}
	public Student(int stuNo, String stuName, int stuAge) {
		this.stuNo = stuNo;
		this.stuName = stuName;
		this.stuAge = stuAge;
	}
	public int getStuNo() {
		return stuNo;
	}
	public void setStuNo(int stuNo) {
		this.stuNo = stuNo;
	}
	public String getStuName() {
		return stuName;
	}
	public void setStuName(String stuName) {
		this.stuName = stuName;
	}
	public int getStuAge() {
		return stuAge;
	}
	public void setStuAge(int stuAge) {
		this.stuAge = stuAge;
	}
	
}
