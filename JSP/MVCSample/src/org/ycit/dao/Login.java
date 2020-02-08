package org.ycit.dao;
//javaBean封装数据的模型 （对应数据库的表）  用户名和密码
public class Login {
	private int id;
	private	String name;
	private String pwd;
	public Login() {
	}	
	public Login(String name, String pwd) {
		this.name = name;
		this.pwd = pwd;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	
}
