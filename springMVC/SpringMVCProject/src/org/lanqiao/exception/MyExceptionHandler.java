package org.lanqiao.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

//@ControllerAdvice
public class MyExceptionHandler {//���ǿ������������� ���ڴ����쳣����
	
	@ExceptionHandler({Exception.class  })
	public ModelAndView handlerArithmeticException2(Exception e) {
		ModelAndView mv = new ModelAndView("error");
		System.out.println(e +"============"+"��@ControllerAdvice�е��쳣�����������Դ����κ����е��쳣");
		mv.addObject("er", e) ;
		return  mv;
	}
	
}
