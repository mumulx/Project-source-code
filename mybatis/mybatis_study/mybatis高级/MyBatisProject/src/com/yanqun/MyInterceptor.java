package com.yanqun;


import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.jdbc.Null;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.session.ResultHandler;

import java.sql.Statement;
import java.util.Properties;
import java.util.Properties;
@Intercepts({
  //拦截哪个对象的哪个方法
  @Signature(type= StatementHandler.class,method ="parameterize",
          args = {Statement.class})
})
public class MyInterceptor implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("**1MyInterceptor....");
        Object target = invocation.getTarget();
        System.out.println("目标对象："+target);
        MetaObject metaObject = SystemMetaObject.forObject(target);
        Object value = metaObject.getValue("parameterHandler.parameterObject");
        System.out.println("SQL中的参数："+value);
        metaObject.setValue("parameterHandler.parameterObject", 11);
        metaObject.setValue("parameterHandler.boundSql.sql","select * from student where stuno =12");
        metaObject.setValue("parameterHandler.boundSql.parameterObject",11);
        //执行目标方法
        Object proceed = invocation.proceed();
        //返回执行后的返回值
        return proceed;
    }
    @Override
    public Object plugin(Object target) {
        Object wrap =  Plugin.wrap( target,this) ;
        //返回包装后的目标对象，代理
        System.out.println("MyInterceptor...."+wrap);
        return wrap;
    }
    @Override
    public void setProperties(Properties properties) {
//        获取settings配置时的属性
        System.out.println("属性：" + properties);
    }
}
