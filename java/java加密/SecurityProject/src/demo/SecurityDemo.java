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
			System.out.println("�������û���");
			uname= input.next();
			System.out.println("����������");
			upwd= input.next();
			//md5����
			upwd = MD5.md5Encode(upwd.getBytes());
			System.out.println("ע��������ܺ�"+upwd);
			flag = true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return flag;
		
	}
	//��¼
	public  boolean login() {
		boolean flag=false;
		System.out.println("�������û���");
		String loginUname= input.next();
		System.out.println("����������");
		String loginUpwd= input.next();
		loginUpwd = MD5.md5Encode(loginUpwd.getBytes());
		System.out.println("��¼������ܣ�"+loginUpwd);
		
		
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
			System.out.println("��¼�ɹ�");
		}else {
			System.out.println("��¼ʧ��");
		}
	}

}
