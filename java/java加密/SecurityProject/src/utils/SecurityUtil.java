package utils;

public class SecurityUtil {
	//ͨ�������м��ܺͽ���  ����string("abc")--->String("xyz")
	public static String xor(String in) {//"abc"--->{'a','b','c'}�ַ�������ַ�����
		//char ��int֮��ʱ����ֱ��ת����
		//String ��int֮��ʱ�޷����������
		char[] chs = in.toCharArray();
		for(int i = 0; i < chs.length;i++) {
			//^ ������
			chs[i] = (char)(chs[i]^3000);
		}
		return new String(chs);
	}
	public static void main(String[] args) {
		String str = "hello";
		str = xor(str);// ��һ�����  ����
		System.out.println(str);
		
		str = xor(str);// �ڶ������  ����
		System.out.println(str);
		
	}
	
	
}
