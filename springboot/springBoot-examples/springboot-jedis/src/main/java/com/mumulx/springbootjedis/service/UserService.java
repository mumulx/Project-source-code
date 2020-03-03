package com.mumulx.springbootjedis.service;


import com.mumulx.springbootjedis.entity.User;

public interface UserService {

    /*
    * Redis String 类型
    *
    * 需求：用户输入一个key
    *
    *先判断Redis中是否存在该数据，
    *
    * 如果存在，在redis中进行查询，并返回
    *
    * 如果不存在，在MySql数据库进行查询，将结果献给Redis，并返回
    *
    *
    *
    * */
    public  String getString (String key);

    /**
     *
     *测试String类型
     * 需求：用户输入一个Redis数据，该key的有效期为24小时
     * */
    public void expriseString (String key,String value);
    public User selectById(String id);
}
