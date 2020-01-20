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
	// ��ȡ�������˽ӿڵĶ�̬�������
	// serviceInterface:����Ľӿ���
	// addr:���������˵�ip���˿�
	@SuppressWarnings("unchecked")
	public static <T> T getRemoteProxyObj(Class serviceInterface, InetSocketAddress addr) {

		// ��̬�������
		/*
		 * newProxyInstance(a,b,c) a:�����������Ҫ������һ���� b:��Ҫ����Ķ��������Щ����(����) --- �ӿڣ������ڽӿ��д�� ��
		 * 
		 */
		return (T) Proxy.newProxyInstance(serviceInterface.getClassLoader(), new Class<?>[] {serviceInterface },
				new InvocationHandler() {
					/**
					 * proxy ����Ķ���
					 * 
					 * method �������һ������
					 * 
					 * args �����б�
					 * 
					 */
					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						ObjectInputStream input = null;
						ObjectOutputStream output = null;
						Socket socket = new Socket();
						try {
							// �ͻ��������˷�����������ĳһ������Ľӿ�
							
							// socketAddress:Ip���˿�
							socket.connect(addr);
							output = new ObjectOutputStream(socket.getOutputStream());// ���� ͨ�����л���(������)
							// ���ͣ��ӿ������������������Ĳ���������,�����Ĳ�����
							output.writeUTF(serviceInterface.getName());
							output.writeUTF(method.getName());
							output.writeObject(method.getParameterTypes());
							output.writeObject(args);
							// �ȴ�����˴���
							// ���շ���˴����ķ���ֵ
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
