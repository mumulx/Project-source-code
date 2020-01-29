package org.lanqiao.aop;

import java.lang.reflect.Method;

import org.springframework.aop.ThrowsAdvice;

public class LogException implements ThrowsAdvice {
	//异常通知的具体方法
	public void afterThrowing(Method method, Object[] args ,Object target, NullPointerException ex)//只捕获NullPointerException类型的异常
	{
		System.out.println("00000000000异常通知：目标对象:"+target+",方法名："+method.getName()+",方法的参数个数："+args.length+",异常类型:"+ex.getMessage());
	}
}
