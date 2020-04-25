package qrcode;

import java.awt.image.BufferedImage;

import jp.sourceforge.qrcode.data.QRCodeImage;
public class TwoDimensionCodeImage implements QRCodeImage {  
    BufferedImage bufImg;  //将图片加载到内存中
    public TwoDimensionCodeImage(BufferedImage bufImg) {  
        this.bufImg = bufImg;  
    }  
    @Override  
    public int getHeight() {  
        return bufImg.getHeight();  
    }  
    @Override  
    public int getPixel(int x, int y) {  
        return bufImg.getRGB(x, y);  
    }  
    @Override  
    public int getWidth() {  
        return bufImg.getWidth();  
    }  
}  