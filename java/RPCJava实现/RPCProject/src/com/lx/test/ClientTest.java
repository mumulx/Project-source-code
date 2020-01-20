package com.lx.test;

import java.net.InetSocketAddress;

import com.lx.client.Client;
import com.lx.service.HelloService;
import com.lx.service.HelloServiceImpl;

public class ClientTest {

	public static void main(String[] args) throws ClassNotFoundException {
		
		HelloService helloService = Client.getRemoteProxyObj(Class.forName("com.lx.service.HelloService"), new InetSocketAddress("127.0.0.1", 9999));
		System.out.println(helloService.sayHi("zs"));
		
	}

}
