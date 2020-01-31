package org.lanqiao.entity;

public class Student {
    private Short stuno;

    private String stuname;

    private Short stuage;

    private String graname;

    private Short stusex;

    private String homeaddress;

    private String schooladdress;

    private Short cardid;

    private Short classid;

    public Short getStuno() {
        return stuno;
    }

    public void setStuno(Short stuno) {
        this.stuno = stuno;
    }

    public String getStuname() {
        return stuname;
    }

    public void setStuname(String stuname) {
        this.stuname = stuname == null ? null : stuname.trim();
    }

    public Short getStuage() {
        return stuage;
    }

    public void setStuage(Short stuage) {
        this.stuage = stuage;
    }

    public String getGraname() {
        return graname;
    }

    public void setGraname(String graname) {
        this.graname = graname == null ? null : graname.trim();
    }

    public Short getStusex() {
        return stusex;
    }

    public void setStusex(Short stusex) {
        this.stusex = stusex;
    }

    public String getHomeaddress() {
        return homeaddress;
    }

    public void setHomeaddress(String homeaddress) {
        this.homeaddress = homeaddress == null ? null : homeaddress.trim();
    }

    public String getSchooladdress() {
        return schooladdress;
    }

    public void setSchooladdress(String schooladdress) {
        this.schooladdress = schooladdress == null ? null : schooladdress.trim();
    }

    public Short getCardid() {
        return cardid;
    }

    public void setCardid(Short cardid) {
        this.cardid = cardid;
    }

    public Short getClassid() {
        return classid;
    }

    public void setClassid(Short classid) {
        this.classid = classid;
    }
}