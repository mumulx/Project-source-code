package zxing;

import java.io.File;
import java.io.IOException;

import com.google.zxing.WriterException;

public class Test {
	public static void main(String[] args) throws Exception {
		String imgPath = "src/��Ⱥ.png" ;
		String content = "hello���" ;
		String logo = "src/logo.png" ;
		//���ܣ�����->��ά��
		ZXingUtil.encodeImg(imgPath,"gif",content,430,430,logo);
		
		//���ܣ���ά��->����
		ZXingUtil.decodeImg(new File(imgPath));
		
	}
}
