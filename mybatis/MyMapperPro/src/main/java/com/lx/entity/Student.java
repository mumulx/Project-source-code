package com.lx.entity;


import javax.persistence.*;

@Table(name = "student1")
public class Student {
    /*
    * 主键：@Id
    * 字段：@Column
    *
    * 属性的驼峰命名  ---》 字段的下划线命名
    * 主键 必须是包装类   Integer  不能是   基本类型  int
    * 只支持单表操作
    * @GeneratedValue   主键回写
    *
    *
    * @Transient  表示该属性不会进入数据库持久化操作
    *持久化   student-->数据库
    * 瞬态  (内存)
    *
    * */
    //主键 必须是包装类   Integer  不能是   基本类型  int
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer stuNo;
    @Column(name = "stu_name")
    private  String stuName;
    @Column(name = "stu_age")
    private int stuAge;
    @Transient
    private String other;
    public Student() {

    }

    public Student(String stuName, int stuAge) {
        this.stuName = stuName;
        this.stuAge = stuAge;
    }

    public Student(Integer stuNo, String stuName, int stuAge) {
        this.stuNo = stuNo;
        this.stuName = stuName;
        this.stuAge = stuAge;
    }

    public Integer getStuNo() {
        return stuNo;
    }

    public void setStuNo(Integer stuNo) {
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

    @Override
    public String toString() {
        return "Student{" +
                "stuNo=" + stuNo +
                ", stuName='" + stuName + '\'' +
                ", stuAge=" + stuAge +
                '}';
    }
}
