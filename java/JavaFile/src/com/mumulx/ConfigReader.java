package com.mumulx;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ConfigReader {

	public static void main(String[] args) throws IOException {
		File configFile = new File("d:\\splitDir\\9.config");
		
		readConfig(configFile);
	}
	public static void readConfig(File configFile) throws IOException {
		
		BufferedReader bufferedReader = new BufferedReader(new FileReader(configFile));
		String line = null;
		while((line=bufferedReader.readLine())!=null) {
			
			String[] arr  = line.split("=");
			if(line.startsWith("filename")) {
				
			}else if(line.startsWith("partcount")) {
				
			}else {
				System.out.println("暂未处理");
			}
			
		}
		bufferedReader.close();
		
		
		
		
		
		
	}
}
