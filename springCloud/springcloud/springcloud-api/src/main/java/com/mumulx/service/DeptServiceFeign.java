package com.mumulx.service;

import com.mumulx.entity.Dept;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.List;
//@Component
@FeignClient(value = "SPRINGCLOUD-PROVIDER-DEPT",fallbackFactory = DeptServiceFeignFallbackFactory.class)
public interface DeptServiceFeign {

    @GetMapping("/dept/get/{id}")
    public Dept queryId(@PathVariable("id") Long id);

    @GetMapping("/dept/list")
    public List<Dept> queryAll();

    @PostMapping("/dept/list")
    public boolean addDept(Dept dept);
}
