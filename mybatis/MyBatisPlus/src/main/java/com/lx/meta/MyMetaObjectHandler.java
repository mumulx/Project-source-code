package com.lx.meta;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;

public class MyMetaObjectHandler implements MetaObjectHandler {

    //增加时，给没有赋值的字段 加默认值
    @Override
    public void insertFill(MetaObject metaObject) {
        System.out.println("start insert fill ....");
        /* 上面选其一使用,下面的已过时(注意 strictInsertFill 有多个方法,详细查看源码) */
        //this.setFieldValByName("operator", "Jerry", metaObject);
        this.setInsertFieldValByName("stuName", "ZHang", metaObject);
    }

    //更新时，给没有赋值的字段 加默认值
    @Override
    public void updateFill(MetaObject metaObject) {
        System.out.println("start update fill ....");
        /* 上面选其一使用,下面的已过时(注意 strictUpdateFill 有多个方法,详细查看源码) */
        //this.setFieldValByName("operator", "Tom", metaObject);
        this.setUpdateFieldValByName("stuName", "ZHang", metaObject);
    }
}
