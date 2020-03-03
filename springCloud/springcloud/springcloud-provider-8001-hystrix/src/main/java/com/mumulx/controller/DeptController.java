package com.mumulx.controller;
import com.mumulx.entity.Dept;
import com.mumulx.service.DeptServiceFeign;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

//@RestController = @Controller + @ResponseBody组成,
// @RestController注解Controller，则Controller中的方法无法返回jsp页面，
//这里没有前端页面使用@RestController就够了
@RestController
public class DeptController {
    @Resource(name = "deptService")
    private DeptServiceFeign deptService;

    @HystrixCommand(fallbackMethod = "getHystrix")//指定备选方法
    @GetMapping("/dept/get/{id}")
    public Dept get(@PathVariable("id") Long id){

        Dept dept = deptService.queryId(id);
        if (dept == null) {
            throw new RuntimeException("id=>" + id + ",不存在该用户，或信息无法找到");
        }
        return dept;
    }
    //备选方法
    public Dept getHystrix(@PathVariable("id") Long id){

        return new Dept().setDeptno(id).setDname("id=>"+id+",没有对应的信息，null~~").setDb_source("不存在该数据库");
    }
}
