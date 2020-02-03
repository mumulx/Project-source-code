package org.boot.mumu.entity;
/**
* @author lx 1819778796@qq.com
* @version 创建时间：2020年2月3日 下午8:55:11
* 类说明
*/
public class Product {
	private String name;
	private double price;
	private int inStock;
	public Product() {
	}
	public Product(String name, double price, int inStock) {
		this.name = name;
		this.price = price;
		this.inStock = inStock;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getInStock() {
		return inStock;
	}
	public void setInStock(int inStock) {
		this.inStock = inStock;
	}
	
}
