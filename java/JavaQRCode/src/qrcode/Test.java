package qrcode;

public class Test {
	public static void main(String[] args)  throws Exception{
		//���ɶ�ά��
		/*
		 * ����ͼƬ��·��        src/��ά��.png
		 * ������Ϣ����ַ��Ϣ ��  "helloworld"
		 */
		String imgPath = "src/��ά��123.png"; 
		String content =  "http://www.baidu.com";  //ɨ���ά�����ҳ��ת
		//���ɶ�ά��
		/*
		 * ���ܣ�  ������Ϣ ->��ά�� 
		 * ���ܣ�  ��ά�� -> ������Ϣ 
		 */
		QRCodeUtil qrUtil = new QRCodeUtil();
		//���ܣ�  ������Ϣ ->��ά�� 
		qrUtil.encoderQRCode(content, imgPath, "png", 17);
//		   TwoDimensionCode handler = new TwoDimensionCode();  
//		   handler.encoderQRCode(content, imgPath, "png", 7);
//		//���ܣ�  ��ά�� -> ������Ϣ 
		String imgContent = qrUtil.decoderQRCode(imgPath) ;
		System.out.println(imgContent);
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
