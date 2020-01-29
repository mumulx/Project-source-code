package org.lanqiao.aop;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

public class LogSchema {
	
	//后置通知方法  :JoinPoint适用于注解
	public void afterReturning(JoinPoint jp,Object returnValue) throws Throwable {
		System.out.println("》》》》》》》》》》》后置通知：目标对象："+jp.getThis()+",调用的方法名："+jp.getSignature().getName()+",方法的参数个数："+jp.getArgs().length+"，方法的返回值："+returnValue);
	}
	
	public void before() {
		System.out.println("》》》》》》》》》》》前置通知...");
	}
	
	public void whenException(JoinPoint jp,NullPointerException e) {
		System.out.println(">>>>>>>>>>>>>>>>异常：" +e.getMessage());
	}
	//注意：环绕通知 会返回目标方法的返回值，因此返回值为Object
	public Object around(ProceedingJoinPoint jp)    {
		System.out.println("''''''''''''''''''环绕通知：前置通知");
		Object result = null ; 
		try {
			 result = jp.proceed() ;//执行方法
			 System.out.println("'''''''''"+jp.getSignature().getName()+","+result);
			System.out.println("''''''''''''''''''环绕通知：后置通知");
		}catch(Throwable e) {
			System.out.println("''''''''''''''''''环绕通知：异常通知");
		}
		return result ;
	}

}
