package md5;

import org.apache.commons.codec.digest.DigestUtils;

public class MD5 {
	//md5
	public static String md5Encode(byte[] input) {
		
		return DigestUtils.md5Hex(input);//byte[]--->String
		
	}
	//SHA256
	public static String SHA256Encode(byte[] input) {
		
		return DigestUtils.sha256Hex(input);//byte[]--->String
		
	}	
	
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str = "hello";
		//str = md5Encode(str.getBytes());
		//System.out.println(str);
		
		str = SHA256Encode(str.getBytes());
		System.out.println(str);
	}

}
