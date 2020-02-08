package org.student.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

//要想将 一个普通的class 编程一个 具有特定功能的类（过滤器、拦截....），要么继承 父类、要么实现一个接口、要么增加一个注解
public class MyFilter implements Filter {//过滤器
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("filter..init..");
	}
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("拦截请求......");
		chain.doFilter(request, response);//放行
		
		
		System.out.println("拦截响应......");
		
	}

	@Override
	public void destroy() {
		System.out.println("filter..destroy..");
	}

}
