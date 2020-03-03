package com.mumulx.controller;

import com.mumulx.entity.Dept;
import com.mumulx.service.DeptServiceFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class DeptController {


    @Autowired
    private DeptServiceFeign service =null;

    @RequestMapping("/consumer/dept/get/{id}")
    public Dept get(@PathVariable("id") Long id) {
        return this.service.queryId(id);
    }

    @RequestMapping("/consumer/dept/add")
    public boolean add(Dept dept) {
        return this.service.addDept(dept);
    }

    @RequestMapping("/consumer/dept/list")
    public List<Dept> list() {
        return this.service.queryAll();
    }
}
