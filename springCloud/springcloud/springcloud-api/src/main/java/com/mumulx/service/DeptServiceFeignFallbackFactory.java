package com.mumulx.service;

import com.mumulx.entity.Dept;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

//降级
@Component
public class DeptServiceFeignFallbackFactory implements FallbackFactory {

    @Override
    public DeptServiceFeign create(Throwable throwable) {
        // 谁失败了就返回谁


        return new DeptServiceFeign() {
            @Override
            public Dept queryId(Long id) {
                return new Dept()
                        .setDeptno(id)
                        .setDname("id=>"+id+"没有对应的信息，客户端提供了降级的信息，这个服务现在已经被关闭了")
                        .setDb_source("n没有数据");
            }

            @Override
            public List<Dept> queryAll() {
                return null;
            }

            @Override
            public boolean addDept(Dept dept) {
                return false;
            }
        };
    }
}
