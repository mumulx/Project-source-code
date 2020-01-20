package com.lx.service;

public class HelloServiceImpl implements HelloService{

	@Override
	public String sayHi(String name) {
		// TODO Auto-generated method stub
		System.out.println("hi"+name);
		return "hi"+name;
	}

}
