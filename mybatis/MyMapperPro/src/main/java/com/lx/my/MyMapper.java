package com.lx.my;

import com.lx.mapper.MySelectOne;
import tk.mybatis.mapper.common.base.BaseDeleteMapper;
import tk.mybatis.mapper.common.base.BaseSelectMapper;

public interface MyMapper<T> extends BaseSelectMapper<T>, BaseDeleteMapper<T>, MySelectOne<T> {

}
