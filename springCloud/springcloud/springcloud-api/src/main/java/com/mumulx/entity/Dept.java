package com.mumulx.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor //无参构造函数
@Accessors(chain = true)//支持链式写法
public class Dept {


    private long deptno;    //主键

    private String dname;

    //这个数据存在哪个数据库
    private  String db_source;

    public Dept(String db_source) {
        this.db_source = db_source;
    }

    public Dept(long deptno, String dname, String db_source) {
        this.deptno = deptno;
        this.dname = dname;
        this.db_source = db_source;
    }
/**
     * 链式写法
     * dept.setDeptNo(11).setDname('sss').setDb_source()
     */

}
