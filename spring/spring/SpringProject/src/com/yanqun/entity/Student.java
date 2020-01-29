package com.yanqun.entity;


public class Student {
    //简单类型：  8个基本类型+String
    private int stuNo ;
    private String stuName ;
    private int stuAge;
    private Address address ;


    public Student() {
        System.out.println("student 无参构造");
    }
    public Student(int stuNo, String stuName, int stuAge) {
        this.stuNo = stuNo;
        this.stuName = stuName;
        this.stuAge = stuAge;
        System.out.println("student 有参构造");
    }

    public void myInit(){
        stuName="初始化zs" ;
        System.out.println("init...");
    }
    public void myDestroy(){
        System.out.println("destroy...");
    }
    public int getStuNo() {
        return stuNo;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
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

    @Override
    public String toString() {
        return "Student{" +
                "stuNo=" + stuNo +
                ", stuName='" + stuName + '\'' +
                ", stuAge=" + stuAge +
                ", address=" + address +
                '}';
    }

    public void setStuAge(int stuAge) {
        this.stuAge = stuAge;
    }


}
