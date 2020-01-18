package com.lx.mapper;


import com.lx.entity.Student;
import com.lx.my.MyMapper;
import tk.mybatis.mapper.common.Mapper;

//public interface StudentMapper extends Mapper<Student> {
    //定义指定的功能 仅使用  selece  和delete的功能
public interface StudentMapper extends MyMapper<Student> {
/*
自定义的组件的一般开发模式
1. 开发自定义
2.配置   配置程序  不要在使用默认值了  用自定义的

* */

}
