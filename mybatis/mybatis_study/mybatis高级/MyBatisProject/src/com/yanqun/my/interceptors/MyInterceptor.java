package com.yanqun.my.interceptors;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.session.ResultHandler;

import java.sql.Statement;
import java.util.Properties;
//query(Statement statement, ResultHandler resultHandler)
@Intercepts({
//        @Signature(type = StatementHandler.class , method ="query",args = {Statement.class, ResultHandler.class})
       @Signature(type = StatementHandler.class , method ="parameterize",args = {Statement.class})

})
public class MyInterceptor implements Interceptor {

    //拦截
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("拦截方法...intercept...");


        Object target = invocation.getTarget();//目標方法 ： select * from student where stuNo = #{stuNo}
        System.out.println("目標對象" +target);
        MetaObject metaObject = SystemMetaObject.forObject(target);
//        metaObject.getValue("参数..") ;
        Object value = metaObject.getValue("parameterHandler.parameterObject");
        System.out.println(value+"---------");

        metaObject.setValue("parameterHandler.parameterObject",2);//11->1
        Object value2 = metaObject.getValue("parameterHandler.parameterObject");
        System.out.println(value2+"---------");


        Object proceed = invocation.proceed();//放行
        System.out.println(proceed);
        return proceed;
    }

    @Override
    public Object plugin(Object target) {//将拦截器中定义的 增强功能  和原来的核心对象 合并起来，称为最终的 核心对象

        Object wrap = Plugin.wrap(target, this);
        System.out.println("plugin...."+wrap);
        return wrap;
    }

    @Override
    public void setProperties(Properties properties) {
       // System.out.println("setProperties....");
        System.out.println("设置属性:"+properties); //设置属性...
    }
}
