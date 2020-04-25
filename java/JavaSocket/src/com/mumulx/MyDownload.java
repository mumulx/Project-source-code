package com.mumulx;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

//处理下载的线程
public class MyDownload implements Runnable{
	
	private Socket socket;
	
	public MyDownload() {

	}
	public MyDownload(Socket socket) {
		this.socket = socket;
	}
	@Override
	public void run() {
		System.out.println("客户端连接成功");
		try {
		// 服务端向客户端发送消息Output
		OutputStream out = socket.getOutputStream();
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
		out.close();
		socket.close();
		fileIn.close();
		
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}

}
