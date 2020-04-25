package com.mumulx;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MyService01 {
	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = new ServerSocket(9999);
		while(true) {
			Socket socket = serverSocket.accept();
			//下载的线程
			//MyDownload mydownload =new MyDownload(socket);
			//Runnable-->Thread
			new Thread(new MyDownload(socket)).start();

		}
	}
}
