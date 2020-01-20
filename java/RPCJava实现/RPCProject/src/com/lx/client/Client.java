package com.lx.client;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Client {
	// 获取代表服务端接口的动态代理对象
	// serviceInterface:请求的接口名
	// addr:带请求服务端的ip：端口
	@SuppressWarnings("unchecked")
	public static <T> T getRemoteProxyObj(Class serviceInterface, InetSocketAddress addr) {

		// 动态代理对象
		/*
		 * newProxyInstance(a,b,c) a:类加载器：需要代理哪一个类 b:需要代理的对象具有哪些功能(方法) --- 接口（方法在接口中存放 ）
		 * 
		 */
		return (T) Proxy.newProxyInstance(serviceInterface.getClassLoader(), new Class<?>[] {serviceInterface },
				new InvocationHandler() {
					/**
					 * proxy 代理的对象
					 * 
					 * method 对象的哪一个方法
					 * 
					 * args 参数列表
					 * 
					 */
					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						ObjectInputStream input = null;
						ObjectOutputStream output = null;
						Socket socket = new Socket();
						try {
							// 客户端向服务端发送请求：请求某一个具体的接口
							
							// socketAddress:Ip：端口
							socket.connect(addr);
							output = new ObjectOutputStream(socket.getOutputStream());// 发送 通过序列化流(对象流)
							// 发送：接口名，方法名，方法的参数的类型,方法的参数，
							output.writeUTF(serviceInterface.getName());
							output.writeUTF(method.getName());
							output.writeObject(method.getParameterTypes());
							output.writeObject(args);
							// 等待服务端处理
							// 接收服务端处理后的返回值
							input = new ObjectInputStream(socket.getInputStream());
							Object result = input.readObject();
							return result;
						} catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
							return null;
						} finally {
							try {
								if (output != null) {
									output.close();
								}
								if (input != null) {
									input.close();
								}
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
								return null;
							}
						}
					}
				});

	}

}
