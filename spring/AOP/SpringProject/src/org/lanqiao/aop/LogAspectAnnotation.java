package org.lanqiao.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
//<bean id="logAnnotation" class="org.lanqiao.aop.LogAspectAnnotation">
//@Component("logAnnotation")   //将LogAspectAnnotation纳入springIOC容器中
@Aspect //此类是一个通知
public class LogAspectAnnotation  {
	
	//前置通知
	@Before("execution(public * addStudent(..))") //属性：定义切点
	public void myBefore(JoinPoint jp) {
		System.out.println("《注解形式-前置通知》：目标对象："+jp.getTarget()+",方法名："+jp.getSignature().getName() +",参数列表："+ jp.getArgs().length  );
	}
	//后置通知
	@AfterReturning( pointcut= "execution(public * addStudent(..))" ,returning="returningValue" ) 
	public void myAfter(JoinPoint jp,Object returningValue) {//returningValue是返回值，但需要告诉spring
		System.out.println("《注解形式-后置通知》：目标对象："+jp.getTarget()+",方法名："+jp.getSignature().getName() +",参数列表："+  jp.getArgs().length+",返回值："+returningValue );
	}
	
	/*环绕通知 ,参数ProceedingJoinPoint
	@Around("execution(public * addStudent(..))")
	public void myAround(ProceedingJoinPoint jp  ) {
		//方法之前：前置通知
		System.out.println("《【环绕】方法之前：前置通知");
		
		try {
			//方法执行时
			jp.proceed() ;//执行方法
	
			//方法之前之后：后置通知
			System.out.println("《【环绕】方法之前之后：后置通知");
		}catch(Throwable e) {
			//发生异常时：异常通知
			System.out.println("《【环绕】发生异常时：异常通知");
		}finally {
			//最终通知
			System.out.println("《【环绕】最终通知");
		}
	}*/
	
	//异常通知:如果只捕获特定类型的已存银行，则可以通过第二个参数实现：e
	@AfterThrowing(pointcut= "execution(public * addStudent(..))",throwing="e")
	public void myException(JoinPoint pj, NullPointerException e) {//此异常通知 只会捕获NullPointerException类型的异常
		System.out.println("&&&&&&《注解形式-异常通知》----e:"+e.getMessage());
	}
	
	//最终通知
	@After("execution(public * addStudent(..))")
	public void myAfter() {
		System.out.println("《[myAfter]注解形式-最终通知-----通知》----");
	}
	
	
}
