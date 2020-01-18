package com.lx.entity;

import javax.persistence.*;

@Table(name = "student1")
public class Student1 {
    @Id
    @Column(name = "stu_no")
    @GeneratedValue(generator = "JDBC")
    private Integer stuNo;

    @Column(name = "stu_name")
    private String stuName;

    @Column(name = "stu_age")
    private Integer stuAge;

    /**
     * @return stu_no
     */
    public Integer getStuNo() {
        return stuNo;
    }

    /**
     * @param stuNo
     */
    public void setStuNo(Integer stuNo) {
        this.stuNo = stuNo;
    }

    /**
     * @return stu_name
     */
    public String getStuName() {
        return stuName;
    }

    /**
     * @param stuName
     */
    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    /**
     * @return stu_age
     */
    public Integer getStuAge() {
        return stuAge;
    }

    /**
     * @param stuAge
     */
    public void setStuAge(Integer stuAge) {
        this.stuAge = stuAge;
    }

    @Override
    public String toString() {
        return "Student1{" +
                "stuNo=" + stuNo +
                ", stuName='" + stuName + '\'' +
                ", stuAge=" + stuAge +
                '}';
    }
}