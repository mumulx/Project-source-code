package com.mumulx.service.impl;

import com.mumulx.entity.Dept;
import com.mumulx.mapper.DeptMapper;
import com.mumulx.service.DeptServiceFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value="deptService")
public class DeptServiceImpl implements DeptServiceFeign {
    @Autowired
    private DeptMapper deptMapper;
    @Override
    public boolean addDept(Dept dept) {
        return deptMapper.addDept(dept);
    }
    @Override
    public Dept queryId(Long id) {
        return deptMapper.queryId(id);
    }
    @Override
    public List<Dept> queryAll() {
        return deptMapper.queryAll();
    }
}
