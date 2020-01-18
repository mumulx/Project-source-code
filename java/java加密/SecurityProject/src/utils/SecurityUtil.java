package utils;

public class SecurityUtil {
	//通过异或进行加密和解密  传入string("abc")--->String("xyz")
	public static String xor(String in) {//"abc"--->{'a','b','c'}字符串变成字符数组
		//char 和int之间时可以直接转换的
		//String 和int之间时无法进行运算的
		char[] chs = in.toCharArray();
		for(int i = 0; i < chs.length;i++) {
			//^ 异或符号
			chs[i] = (char)(chs[i]^3000);
		}
		return new String(chs);
	}
	public static void main(String[] args) {
		String str = "hello";
		str = xor(str);// 第一次异或  加密
		System.out.println(str);
		
		str = xor(str);// 第二次异或  解密
		System.out.println(str);
		
	}
	
	
}
