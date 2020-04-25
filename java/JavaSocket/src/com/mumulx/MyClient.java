package com.mumulx;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import com.sun.org.apache.bcel.internal.generic.NEW;

public class MyClient {

	public static void main(String[] args) throws UnknownHostException, IOException {
		//客户端连接server发布的服务
		Socket socket = new Socket("127.0.0.1",9999);
		//客户端接收消息
		
		InputStream in = socket.getInputStream();
		//接收普通文字
		//byte[] bs = new byte[100];
		//in.read(bs);
		//System.out.println("客户端接收到的消息为："+new String(bs));
		//接收文件
		OutputStream fileOut = new FileOutputStream("D:\\aa.prt");
		byte[] bs = new byte[1000];
		int len  =-1;
		while((len=in.read(bs))!=-1) {
			fileOut.write(bs,0,len);
			
		}
		System.out.println("文件接收成功");
		
		
		
		
		//客户端向服务端返回信息
		/*
		 * OutputStream out = socket.getOutputStream(); String info = "hello Server";
		 * out.write(info.getBytes());
		 */
		
		
		in.close();
		//out.close();
		socket.close();
		fileOut.close();
	
	
	
	
	
	
	
	
	
	
	
	}
}
