package com.mumulx.mapper;

import com.mumulx.entity.Dept;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper     //将dao交给spring管理    不用写mapper.xml了      自动注册一个接口实现类
@Repository //将dao类声明为bean
public interface DeptMapper {

    boolean addDept(Dept dept);

    Dept queryId(Long id);

    List<Dept> queryAll();
}
