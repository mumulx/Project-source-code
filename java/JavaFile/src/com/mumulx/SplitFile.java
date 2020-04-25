package com.mumulx;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class SplitFile {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		// 源文件(待拆分的文件)
		File resFile = new File("d:\\data.zip");
		// 拆分后的目录
		File splitDir = new File("d:\\splitDir");
		// splitFile(resFile,splitDir);
		// 限制使用的次数：5
		
		  if(hasRemainingTries()) {
			  splitFile(resFile,splitDir); 
			  System.out.println("拆分文件成功！！");
		  }else {
			  System.out.println("使用次数已到！！");
		  }
		 
	}

	// 判断是否还有试用次数
	// 思路：将当前的次数保存再硬盘中，然后mei一次使用时和次数比较
	public static boolean hasRemainingTries() throws FileNotFoundException, IOException {
		Properties prop = new Properties();
		int count = 0;
		// 每使用一次：1.先获取之前使用了几次(3),2.再将之前的次数+1(4)
		// 查询之前已经使用了几次
		prop.load(new FileInputStream("D:\\splitDir\\times.properties"));
		String times = prop.getProperty("times");
		if (times == null) {
			count = 1;
			prop.setProperty("times", count+ "");
		} else {
			int timeCount = Integer.parseInt(times);
			timeCount++;
			prop.setProperty("times",timeCount+"");
			if (timeCount > 5) {
				return false;
			}
		}
		prop.store(new FileOutputStream("D:\\splitDir\\times.properties"), "try set times");
		return true;
	}

	// 拆分文件
	public static void splitFile(File resFile, File splitDir) throws IOException {
		if (!splitDir.exists()) {
			splitDir.mkdir();
		}
		// 思路：拆分：1个输入流，n个输出流(a,b,c)
		// 合并：n个输入流，1和输出流(注意顺序a,b,c)

		// 拆分：1个输入流
		InputStream in = new FileInputStream(resFile);
		OutputStream out = null;
		byte[] buf = new byte[1024 * 1024];
		int len = -1;
		int count = 1;
		while ((len = in.read(buf)) != -1) {
			out = new FileOutputStream(new File(splitDir, count++ + ".part"));

			out.write(buf, 0, len);
			// out.flush();//专门的清理缓冲区
			out.close();// 关闭流、关闭之前会清理缓冲区

		}
		// 拆分的时候：如何将文件名，分割的数量保留，为后续的合并做准备
		// 再生成一个配置文件conf.properties保存上述信息
		/*
		 * //方式一 out = new FileOutputStream(new File(splitDir,"conf.properties"));
		 * //查询当前操作系统的换行符 String property = System.getProperty("line.separator");
		 * out.write(("filename="+resFile.getName()).getBytes());
		 * out.write(property.getBytes());
		 * 
		 * out.write(("partcount="+(count-1)).getBytes()); out.close();
		 */
		// 方式二Properties，将内存中的多个属性以key=value的形式写到硬盘中
		out = new FileOutputStream(new File(splitDir, "conf.properties"));
		Properties prop = new Properties();
		prop.setProperty("filename", resFile.getName());
		prop.setProperty("partcount", (count - 1) + "");
		// 写入磁盘（保存：持久化）
		prop.store(out, "file configuration...");
		out.close();

		in.close();

	}

}
