package com.lx.mapper;

import org.apache.ibatis.annotations.SelectProvider;
import tk.mybatis.mapper.annotation.RegisterMapper;
import tk.mybatis.mapper.provider.base.BaseSelectProvider;
@RegisterMapper
public interface MySelectOne<T> {
    @SelectProvider(
            type = MySelectProvider.class,
            method = "dynamicSQL"
    )
    T mySelectOne(T var1);
}
