package com.yanqun.my.interceptors;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;

import java.sql.Statement;
import java.util.Properties;

//query(Statement statement, ResultHandler resultHandler)
@Intercepts({
        @Signature(type = StatementHandler.class , method ="query",args = {Statement.class, ResultHandler.class})
})
public class MyInterceptor2 implements Interceptor {

    //拦截
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("=====222==拦截方法...intercept...");
        Object proceed = invocation.proceed();//放行
        System.out.println(proceed);
        return proceed;
    }

    @Override
    public Object plugin(Object target) {//将拦截器中定义的 增强功能  和原来的核心对象 合并起来，称为最终的 核心对象
        System.out.println("$$$$$plugin$$$$");
        Object wrap = Plugin.wrap(target, this);
        return wrap;
    }

    @Override
    public void setProperties(Properties properties) {

//        System.out.println("$$$$$setProperties$$$$");
        System.out.println("$$$$2设置属性:"+properties); //设置属性...
    }
}
