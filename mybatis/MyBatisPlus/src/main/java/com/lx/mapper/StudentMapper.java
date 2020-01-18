package com.lx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lx.entity.Student;

public interface StudentMapper extends BaseMapper<Student> {
// BaseMapper默认有17个方法--->默认被注入在MP中
    //自己编写了一个新的：  ----》 也想要被注入进去

    public  void deleteAllStudents();




}
