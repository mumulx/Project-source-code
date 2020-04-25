package com.mumulx;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.SequenceInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

public class MergeFile {

	public static void main(String[] args) throws IOException {
		//文件合并方法一
//		//读取多个拆分后的文件（inouts:所有输入流的集合）
//		List<FileInputStream> inputs = new ArrayList<>();
//		for(int i =1;i<=8;i++) {
//			inputs.add(new FileInputStream("D:\\splitDir\\"+i+".part"));
//			
//		}
//		//指定合并后的文件输出流
//		OutputStream out = new FileOutputStream("d:\\asd.zip");
//		//将多个输入流一次读入内存，最后再一次性输出到asd.zip中
//		byte[] buf = new byte[1024*1024];
//		for(FileInputStream in:inputs) {
//			int len = in.read(buf);
//			out.write(buf,0,len);
//		}
//		out.close();
//		for(FileInputStream in:inputs) {
//			in.close();
//		}
//		
		//文件合并方法二
		File splitDir = new File("D:\\splitDir");
		//mergeFile(splitDir);
		mergeFile1(splitDir);

	}
	public static void mergeFile(File splitDir) throws IOException {

		List<FileInputStream> inputs = new ArrayList<>();
		for(int i =1;i<=8;i++) {
			inputs.add(new FileInputStream("D:\\splitDir\\"+i+".part"));
			
		}
		Enumeration<FileInputStream> en = Collections.enumeration(inputs);
		//多个流--》1个流
		SequenceInputStream sin = new SequenceInputStream(en);
		//指定合并后的文件输出流
		OutputStream out = new FileOutputStream("d:\\asd.zip");
		//将多个输入流一次读入内存，最后再一次性输出到asd.zip中
		byte[] buf = new byte[1024*1024];
		int len = -1;
		while((len=sin.read(buf))!=-1) {
			out.write(buf,0,len);
			
		}
		out.close();
		sin.close();

	}
	
	public static Properties getProperties() throws FileNotFoundException, IOException {
		//找到配置文件的位置
		String configFileName = "D:\\splitDir\\conf.properties";
		Properties properties= new Properties();
		properties.load(new FileInputStream(configFileName));
		return properties;
		
		
	}
	public static void mergeFile1(File splitDir) throws FileNotFoundException, IOException {
		//合并之前先读取配置文件的信息
		Properties prop = getProperties();
		String fileName = prop.getProperty("filename");
		int partCount = Integer.parseInt(prop.getProperty("partcount"));

		List<FileInputStream> inputs = new ArrayList<>();
		for(int i =1;i<=partCount;i++) {
			inputs.add(new FileInputStream("D:\\splitDir\\"+i+".part"));
			
		}
		Enumeration<FileInputStream> en = Collections.enumeration(inputs);
		//多个流--》1个流
		SequenceInputStream sin = new SequenceInputStream(en);
		//指定合并后的文件输出流
		OutputStream out = new FileOutputStream("d:\\aa\\"+fileName);
		//将多个输入流一次读入内存，最后再一次性输出到asd.zip中
		byte[] buf = new byte[1024*1024];
		int len = -1;
		while((len=sin.read(buf))!=-1) {
			out.write(buf,0,len);
			
		}
		out.close();
		sin.close();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
