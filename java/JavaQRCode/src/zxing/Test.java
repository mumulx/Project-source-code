package zxing;

import java.io.File;
import java.io.IOException;

import com.google.zxing.WriterException;

public class Test {
	public static void main(String[] args) throws Exception {
		String imgPath = "src/颜群.png" ;
		String content = "hello你好" ;
		String logo = "src/logo.png" ;
		//加密：文字->二维码
		ZXingUtil.encodeImg(imgPath,"gif",content,430,430,logo);
		
		//解密：二维码->文字
		ZXingUtil.decodeImg(new File(imgPath));
		
	}
}
