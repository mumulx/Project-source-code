package com.mumulx;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServer {

	public static void main(String[] args) throws IOException {
		// 绑定了服务端的端口：ip：为本机的IP
		// 暴漏了一个服务，该 服务的地址：localhost:9999
		ServerSocket serverSocket = new ServerSocket(9999);
		while (true) {
			Socket accept = serverSocket.accept();
			System.out.println("客户端连接成功");
			// 服务端向客户端发送消息Output

			OutputStream out = accept.getOutputStream();
			String info = "hello";
			/*
			 * out.write(info.getBytes());
			 */
			// 发送文件
			// 准备要发送的文件
			File file = new File("D:\\_model2.prt");
			// 将文件放入内存中

			InputStream fileIn = new FileInputStream(file);
			byte[] FileBytes = new byte[1000];
			int len = -1;
			// 发送（因为文件较大，不能一次发送完毕，因此需要通过循环来分次发送）
			while ((len = fileIn.read(FileBytes)) != -1) {
				out.write(FileBytes);
			}
			System.out.println("文件发送成功");

			// 服务端接收客户端的信息
			/*
			 * InputStream in = accept.getInputStream(); byte[] bs = new byte[100];
			 * in.read(bs); System.out.println("服务端接收到的消息为："+new String(bs));
			 */

			// in.close();
			out.close();
			accept.close();
			serverSocket.close();
			fileIn.close();

		}

	}
}
