package com.lx.center;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

//��������
//
public interface MyCenter {
	public void start();
	public void stop();
	//ע�����
	public void register(Class name,Class serviceImpl);

}
