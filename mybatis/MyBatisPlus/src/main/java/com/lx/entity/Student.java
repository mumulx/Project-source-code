package com.lx.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
// ,keepGlobalPrefix = true         启用全局配置前缀
@TableName(value = "student")
public class Student extends Model<Student> {
    //主键  并且自增
    @TableId(value = "stu_no",type = IdType.AUTO)
    private  int stuNo;
    @TableField(value = "stu_name",fill = FieldFill.INSERT)
    private  String stuName;
    @TableField(value = "stu_age")
    private int stuAge;
    //自动填充,fill = FieldFill.INSERT
    //和数据库无关
    @TableField(exist = false)
    private boolean sex;

    @Version
    private Integer version;
    //逻辑删除字段
    @TableLogic(value = "logic_delete")
    private int logicDelete;


    //初始值不同
    // int : 0   Integer:null

    public Student() {
    }
    public Student( String stuName, int stuAge) {
        this.stuName = stuName;
        this.stuAge = stuAge;
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

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public int getLogicDelete() {
        return logicDelete;
    }

    public void setLogicDelete(int logicDelete) {
        this.logicDelete = logicDelete;
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
