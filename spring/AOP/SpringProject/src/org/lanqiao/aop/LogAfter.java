package org.lanqiao.aop;

import java.lang.reflect.Method;

import org.springframework.aop.AfterReturningAdvice;

public class LogAfter implements AfterReturningAdvice{

	@Override
	public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
		System.out.println("**********后置通知：目标对象："+target+",调用的方法名："+method.getName()+",方法的参数个数："+args.length+"，方法的返回值："+returnValue);
	}

}
