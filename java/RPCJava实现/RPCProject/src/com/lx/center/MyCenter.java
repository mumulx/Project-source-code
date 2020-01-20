package com.lx.center;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

//服务中心
//
public interface MyCenter {
	public void start();
	public void stop();
	//注册服务
	public void register(Class name,Class serviceImpl);

}
