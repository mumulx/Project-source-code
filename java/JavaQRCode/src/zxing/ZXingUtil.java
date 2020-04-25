package zxing;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Binarizer;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.EncodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import jp.sourceforge.qrcode.util.Color;

public class ZXingUtil {
	//加密：文字->二维码（图片）  
	public static void  encodeImg(String imgPath,String format,String content,int width, int height,String logo) throws WriterException, IOException {//format:gif
		Hashtable<EncodeHintType,Object > hints = new Hashtable<EncodeHintType,Object>() ;
		//排错率  L<M<Q<H
		hints.put( EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H) ;
		//编码
		hints.put( EncodeHintType.CHARACTER_SET, "utf-8") ;
		//外边距：margin
		hints.put( EncodeHintType.MARGIN, 1) ;
		/*
		 * content : 需要加密的 文字
		 * BarcodeFormat.QR_CODE:要解析的类型（二维码）
		 * hints：加密涉及的一些参数：编码、排错率
		 */
		BitMatrix bitMatrix = new MultiFormatWriter().encode(content,BarcodeFormat.QR_CODE , width, height,hints) ;
		
		//内存中的一张图片：此时需要的图片 是二维码-> 需要一个boolean[][] ->BitMatrix
		//BufferedImage img = MatrixToImageWriter.toBufferedImage(bitMatrix) ;
		BufferedImage img = new BufferedImage(width, height,  BufferedImage.TYPE_INT_RGB); 
		for(int x=0;x<width;x++) {
			for(int y=0;y<height;y++) {
				img.setRGB(x, y,     (bitMatrix.get(x,y)? Color.BLACK:Color.WHITE)  );
			}
		}
		//画logo
		img = LogoUtil.logoMatrix(img, logo) ;
		//String ->File
		File file = new File(imgPath);
		//生成图片
		ImageIO.write(img, format,file) ;//format:图片格式
	}
	
//	ZXing 
	
	//解密：二维码->文字
	public static void decodeImg(File file) throws Exception {
		if(!file.exists()) return ;
		//file->内存中的一张图片
		 BufferedImage imge = ImageIO.read(file)  ;
		 
		 MultiFormatReader formatReader = new MultiFormatReader() ;
		 LuminanceSource source = new BufferedImageLuminanceSource(imge);
		 Binarizer binarizer = new HybridBinarizer(source);
		 BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);
		 //图片 ->result
		 Map map = new HashMap();
		 map.put(EncodeHintType.CHARACTER_SET, "utf-8") ;
		 Result result = formatReader.decode(binaryBitmap  ,map ) ;
		 System.out.println("解析结果："+ result.toString());
	}
	public static void main(String[] args) {
		int i=6,j=8,k=10,m=7; if(i < j | m > ++k) k++;
		System.out.println(k);
	}
	
	
}
