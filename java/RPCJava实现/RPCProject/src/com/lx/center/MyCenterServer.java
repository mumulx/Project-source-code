package com.lx.center;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.sound.sampled.Port;

public class MyCenterServer implements MyCenter {

	// map:����˵����пɹ��ͻ��˷��ʵĽӿڣ���ע�ᵽmap��
	// key:�ӿڵ����� value:�����Ľӿڵ�ʵ��
	private static HashMap<String, Class> serviceRegister = new HashMap<String, Class>();
	// �˿�
	private static int Port;// =9999
	// ���ӳأ����ӳ��д��ڶ�������Ӷ���ÿһ�����Ӷ��󶼿��Դ���һ���ͻ�����
	// jdk1.5�ṩ
	private static ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

	private static boolean isRunning = false;

	public MyCenterServer(int port) {
		this.Port = port;
	}

	// ��������
	@Override
	public void start() {
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket();
			serverSocket.bind(new InetSocketAddress(Port));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		isRunning = true;
		while (true) {
			// ����ķ������ݣ����տͻ������󣬴������󣬲����ؽ��
			// TODO Auto-generated method stub
			// �����Ҫ�ö���ͻ������󲢷�ִ�� ���߳�
			System.out.println("start server  ...  ");
			// �ͻ���ÿ������һ�����ӣ�����һ�����󣩣������� �����ӳ��л�ȡһ���̶߳���ȥ����
			Socket socket = null;
			try {
				socket = serverSocket.accept();// �ȴ��ͻ�������
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
				

			executor.execute(new ServiceTask(socket));// .������һ�����̶߳���
		}

	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		isRunning = false;
		executor.shutdown();

	}

	@Override
	public void register(Class service, Class serviceImpl) {
		serviceRegister.put(service.getName(), serviceImpl);
	}

	private static class ServiceTask implements Runnable {
		private Socket socket;

		public ServiceTask(Socket socket) {
			this.socket = socket;
		}

		public ServiceTask() {
		}

		@Override
		public void run() {// �߳�����������
			ObjectOutputStream output = null;
			ObjectInputStream input = null;

			// ����ķ������ݣ����տͻ������󣬴������󣬲����ؽ��
			try {
				// ���յ��ͻ������Ӽ����󣬴��������
				input = new ObjectInputStream(socket.getInputStream());
				// ��ΪObjectInputStream�Է������ݵ�˳���ϸ�Ҫ�������Ҫ���շ��͵�˳���������
				String serviceName = input.readUTF();
				String methodName = input.readUTF();
				Class[] parameterTypes = (Class[]) input.readObject();// ��������
				Object[] arguments = (Object[]) input.readObject();// �����Ĳ�����
				// ���ݿͻ��˵������ҵ�map����֮��Ӧ�ľ���Ľӿ�
				Class SerciceClass = serviceRegister.get(serviceName);
				Method method = SerciceClass.getMethod(methodName, parameterTypes);
				// ִ�и÷���
				Object result = method.invoke(SerciceClass.newInstance(), arguments);
				// ��ͻ��� ��ִ����ϵķ���ֵ �����ͻ���
				output = new ObjectOutputStream(socket.getOutputStream());
				output.writeObject(result);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
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
				}

			}
		}
	}

}
