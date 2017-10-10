package com.jumper.hospital.utils;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;

import javax.imageio.ImageIO;

import com.swetake.util.Qrcode;
/**
 * 二维码工具类
 * @author win
 *
 */
public class QRCodeUtils {
	
	public static String CreateQrCodeLocal(String content,String basePath) {
        BufferedImage bufImg = null;
        try {
            Qrcode qrcodeHandler = new Qrcode();
            // 设置二维码排错率，可选L(7%)、M(15%)、Q(25%)、H(30%)，排错率越高可存储的信息越少，但对二维码清晰度的要求越小
            qrcodeHandler.setQrcodeErrorCorrect('M');
            qrcodeHandler.setQrcodeEncodeMode('B');
            // 设置设置二维码尺寸，取值范围1-40，值越大尺寸越大，可存储的信息越大
            qrcodeHandler.setQrcodeVersion(5);
            // 获得内容的字节数组，设置编码格式
            byte[] contentBytes = content.getBytes("utf-8");
            // 图片尺寸
            int imgSize = 67 + 12 * 4;
            bufImg = new BufferedImage(imgSize, imgSize, BufferedImage.TYPE_INT_RGB);
            Graphics2D gs = bufImg.createGraphics();
            // 设置背景颜色
            gs.setBackground(Color.WHITE);
            gs.clearRect(0, 0, imgSize, imgSize);
 
            // 设定图像颜色> BLACK
            gs.setColor(Color.BLACK);
            // 设置偏移量，不设置可能导致解析出错
            int pixoff = 2;
            // 输出内容> 二维码
            if (contentBytes.length > 0 && contentBytes.length < 800) {
                boolean[][] codeOut = qrcodeHandler.calQrcode(contentBytes);
                for (int i = 0; i < codeOut.length; i++) {
                    for (int j = 0; j < codeOut.length; j++) {
                        if (codeOut[j][i]) {
                            gs.fillRect(j * 3 + pixoff, i * 3 + pixoff, 3, 3);
                        }
                    }
                }
            } else {
                throw new Exception("QRCode content bytes length = " + contentBytes.length + " not in [0, 800].");
            }
            gs.dispose();
            bufImg.flush();
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ImageIO.write(bufImg, "png", os);
            //InputStream is = new ByteArrayInputStream(os.toByteArray());
            
            /** 将inputstream保存为本地PNG格式的二维码信息，然后返回路径 **/
        	//File imgFile = new File("d:/test/temp/qr-code/"+TimeUtils.dateFormatStr()+".png");
            String im = "/temp/qr-code/"+TimeUtils.getCurrentTimeStr("yyyyMMdd")+"/"+TimeUtils.dateFormatStr()+".png";
        	File imgFile = new File(basePath+im);
        	if(!imgFile.exists()){
        		imgFile.mkdirs();
        	}
            // 生成二维码QRCode图片    
            ImageIO.write(bufImg, "png", imgFile);   
            
            System.out.println(imgFile.getPath()+"==========imgFile===========");
            
            return im;
            //return imgFile.getPath();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

	public static String CreateQrCode(String content) {
        BufferedImage bufImg = null;
        try {
            Qrcode qrcodeHandler = new Qrcode();
            // 设置二维码排错率，可选L(7%)、M(15%)、Q(25%)、H(30%)，排错率越高可存储的信息越少，但对二维码清晰度的要求越小
            qrcodeHandler.setQrcodeErrorCorrect('M');
            qrcodeHandler.setQrcodeEncodeMode('B');
            // 设置设置二维码尺寸，取值范围1-40，值越大尺寸越大，可存储的信息越大
            qrcodeHandler.setQrcodeVersion(5);
            // 获得内容的字节数组，设置编码格式
            byte[] contentBytes = content.getBytes("utf-8");
            // 图片尺寸
            int imgSize = 67 + 12 * 4;
            bufImg = new BufferedImage(imgSize, imgSize, BufferedImage.TYPE_INT_RGB);
            Graphics2D gs = bufImg.createGraphics();
            // 设置背景颜色
            gs.setBackground(Color.WHITE);
            gs.clearRect(0, 0, imgSize, imgSize);
 
            // 设定图像颜色> BLACK
            gs.setColor(Color.BLACK);
            // 设置偏移量，不设置可能导致解析出错
            int pixoff = 2;
            // 输出内容> 二维码
            if (contentBytes.length > 0 && contentBytes.length < 800) {
                boolean[][] codeOut = qrcodeHandler.calQrcode(contentBytes);
                for (int i = 0; i < codeOut.length; i++) {
                    for (int j = 0; j < codeOut.length; j++) {
                        if (codeOut[j][i]) {
                            gs.fillRect(j * 3 + pixoff, i * 3 + pixoff, 3, 3);
                        }
                    }
                }
            } else {
                throw new Exception("QRCode content bytes length = " + contentBytes.length + " not in [0, 800].");
            }
            gs.dispose();
            bufImg.flush();
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ImageIO.write(bufImg, "png", os);
            InputStream is = new ByteArrayInputStream(os.toByteArray());
            
            String filePath = FastdfsUpload.upoladFile("qr.png", is);
            return filePath;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
	
    public static void main(String[] args) {
		String path = CreateQrCode("http://www.baidu.com");
		System.out.println(path);
	}
    
}
