package zxing;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class LogoUtil {
	//����logo����ά�� ->��logo�Ķ�ά��
	public  static BufferedImage  logoMatrix( BufferedImage matrixImage,String logo ) throws IOException {
		//�ڶ�ά���ϻ�logo:����һ��  ��ά�뻭��
		Graphics2D g2 = matrixImage.createGraphics() ;
		
		//��logo�� String->BufferedImage(�ڴ�)
		BufferedImage logoImg = ImageIO.read(new File(logo)) ;
		int height = matrixImage.getHeight() ;
		int width = matrixImage.getWidth();
		//��logoͼƬ
		g2.drawImage(logoImg  , width*2/5,height* 2/5,    width*1/5,height* 1/5  ,   null) ;
		
		//����һ�� �� ��ɫԲ�������ε� ����
		BasicStroke stroke = new BasicStroke(5,BasicStroke.CAP_ROUND ,BasicStroke.JOIN_ROUND) ;
		//������-���� ����
		g2.setStroke(stroke);
		//����һ��������
		RoundRectangle2D.Float round = new RoundRectangle2D.Float(width*2/5,height* 2/5,    width*1/5,height* 1/5 , BasicStroke.CAP_ROUND ,BasicStroke.JOIN_ROUND);
		g2.setColor(Color.WHITE);
		g2.draw(round);
		
		//��ɫ�߿�
		BasicStroke stroke2 = new BasicStroke(1,BasicStroke.CAP_ROUND ,BasicStroke.JOIN_ROUND) ;
		g2.setStroke(stroke2);
		//����һ��������
		RoundRectangle2D.Float round2 = new RoundRectangle2D.Float(width*2/5+2,height* 2/5+2,    width*1/5-4,height* 1/5 -4, BasicStroke.CAP_ROUND ,BasicStroke.JOIN_ROUND);
//		Color color = new Color(128,128,128) ;
		g2.setColor(Color.GRAY);
		g2.draw(round2);
		
		g2.dispose();
		matrixImage.flush();
				
		return matrixImage;
		
	}
}
