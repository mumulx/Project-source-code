package com.lx.test;

import com.lx.center.MyCenter;
import com.lx.center.MyCenterServer;
import com.lx.service.HelloService;
import com.lx.service.HelloServiceImpl;

public class ServeTest {

	public static void main(String[] args) {

		// ����һ���߳�
		new Thread(new Runnable() {
			@Override
			public void run() {
				// ��������
				MyCenter serverCenter = new MyCenterServer(9999);
				// ��helloService�ӿڼ���ʵ����ע�ᵽ��������
				serverCenter.register(HelloService.class, HelloServiceImpl.class);
				serverCenter.start();
			}
		}).start();

	}

}
