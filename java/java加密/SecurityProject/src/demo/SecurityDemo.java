package demo;

import java.util.Scanner;

import md5.MD5;

public class SecurityDemo {
	 String uname;
	 String upwd;
	 Scanner input = new Scanner(System.in);
	
	public  boolean register() {
		boolean flag = false;
		try {
			System.out.println("请输入用户名");
			uname= input.next();
			System.out.println("请输入密码");
			upwd= input.next();
			//md5加密
			upwd = MD5.md5Encode(upwd.getBytes());
			System.out.println("注册密码加密后："+upwd);
			flag = true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return flag;
		
	}
	//登录
	public  boolean login() {
		boolean flag=false;
		System.out.println("请输入用户名");
		String loginUname= input.next();
		System.out.println("请输入密码");
		String loginUpwd= input.next();
		loginUpwd = MD5.md5Encode(loginUpwd.getBytes());
		System.out.println("登录密码加密："+loginUpwd);
		
		
		System.out.println(loginUname.equals(uname));
		System.out.println(loginUpwd.equals(upwd));
		
		if(loginUname.equals(uname) && loginUpwd.equals(upwd)) {
			
			flag=true;
		}
		return flag;
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SecurityDemo securityDemo = new SecurityDemo();
		securityDemo.register();
		boolean rs = securityDemo.login();
		if(rs) {
			System.out.println("登录成功");
		}else {
			System.out.println("登录失败");
		}
	}

}
