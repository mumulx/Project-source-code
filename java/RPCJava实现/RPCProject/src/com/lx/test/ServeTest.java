package com.lx.test;

import com.lx.center.MyCenter;
import com.lx.center.MyCenterServer;
import com.lx.service.HelloService;
import com.lx.service.HelloServiceImpl;

public class ServeTest {

	public static void main(String[] args) {

		// 开启一个线程
		new Thread(new Runnable() {
			@Override
			public void run() {
				// 服务中心
				MyCenter serverCenter = new MyCenterServer(9999);
				// 将helloService接口及其实现类注册到服务中心
				serverCenter.register(HelloService.class, HelloServiceImpl.class);
				serverCenter.start();
			}
		}).start();

	}

}
