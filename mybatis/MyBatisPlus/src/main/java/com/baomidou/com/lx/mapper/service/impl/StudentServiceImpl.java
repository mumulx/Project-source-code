package com.baomidou.com.lx.mapper.service.impl;

import com.baomidou.com.lx.mapper.entity.Student;
import com.baomidou.com.lx.mapper.mapper.StudentMapper;
import com.baomidou.com.lx.mapper.service.IStudentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jobob
 * @since 2020-01-16
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements IStudentService {

}
