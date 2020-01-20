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

	// map:服务端的所有可供客户端访问的接口，都注册到map中
	// key:接口的名字 value:真正的接口的实现
	private static HashMap<String, Class> serviceRegister = new HashMap<String, Class>();
	// 端口
	private static int Port;// =9999
	// 连接池：连接池中存在多个和连接对象，每一个连接对象都可以处理一个客户请求
	// jdk1.5提供
	private static ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

	private static boolean isRunning = false;

	public MyCenterServer(int port) {
		this.Port = port;
	}

	// 开启服务
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
			// 具体的服务内容；接收客户端请求，处理请求，并返回结果
			// TODO Auto-generated method stub
			// 如果想要让多个客户端请求并发执行 多线程
			System.out.println("start server  ...  ");
			// 客户端每次请求一次连接（发送一次请求），则服务端 从连接池中获取一个线程对象去处理
			Socket socket = null;
			try {
				socket = serverSocket.accept();// 等待客户端连接
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
				

			executor.execute(new ServiceTask(socket));// .参数是一个多线程对象
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
		public void run() {// 线程所做的事情
			ObjectOutputStream output = null;
			ObjectInputStream input = null;

			// 具体的服务内容；接收客户端请求，处理请求，并返回结果
			try {
				// 接收到客户端连接及请求，处理该请求
				input = new ObjectInputStream(socket.getInputStream());
				// 因为ObjectInputStream对发送数据的顺序严格要求，因此需要参照发送的顺序逐个接收
				String serviceName = input.readUTF();
				String methodName = input.readUTF();
				Class[] parameterTypes = (Class[]) input.readObject();// 参数类型
				Object[] arguments = (Object[]) input.readObject();// 方法的参数名
				// 根据客户端的请求，找到map中与之对应的具体的接口
				Class SerciceClass = serviceRegister.get(serviceName);
				Method method = SerciceClass.getMethod(methodName, parameterTypes);
				// 执行该方法
				Object result = method.invoke(SerciceClass.newInstance(), arguments);
				// 向客户端 将执行完毕的返回值 传给客户端
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
