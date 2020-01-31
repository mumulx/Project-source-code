package com.yanqun.entity;

import org.apache.ibatis.type.Alias;


public class Student {
    //学号
    private Integer stuNo; //0   null
    //姓名
    private String stuName;//a:sname    b：nickname
    //年龄
    private int stuAge;
    //年级名称
    private String graName;
    public Student() {
    }
    public Student(Integer stuNo, String stuName) {
        this.stuNo = stuNo;
        this.stuName = stuName;
    }

    public Student(Integer stuNo, String stuName, int stuAge, String graName) {
        this.stuNo = stuNo;
        this.stuName = stuName;
        this.stuAge = stuAge;
        this.graName = graName;
    }

    public Student( String stuName, int stuAge, String graName) {
        this.stuName = stuName;
        this.stuAge = stuAge;
        this.graName = graName;
    }
    // sout(student);

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

    public String getGraName() {
        return graName;
    }

    public void setGraName(String graName) {
        this.graName = graName;
    }

    public String toString()
    {
        return   "学号:"+this.stuNo+"\t姓名:"+this.stuName
                +"\t年龄:"+this.stuAge+"\t年级:"+this.graName;
    }
}
