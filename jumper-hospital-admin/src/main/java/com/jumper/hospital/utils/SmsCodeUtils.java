package com.jumper.hospital.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.apache.log4j.Logger;
/**
 * 短信代码工具类
 * @author win
 *
 */
public class SmsCodeUtils {

	private static final String addr = "http://api.sms.cn/mt/";
	private static final String userId = "jumper";
	
	/*
	 * 如uid是：test，登录密码是：123123 
	   pwd=md5(123123test),即
       pwd=b9887c5ebb23ebb294acab183ecf0769
       
       	线生成地址：http://www.sms.cn/password
	 */
	
	private static final String pwd = "jumper2014";  	
	private static final String encode = "utf8";  
	
	private final static Logger logger = Logger.getLogger(SmsCodeUtils.class);
	
	 public static boolean sendSmsCode(String msgContent, String mobile)
	 {
		 try
		 {
			//组建请求
			String straddr = addr + 
								"?uid="+userId+
								"&pwd="+MD5EncryptUtils.SmsPwdMd5(pwd+userId)+
								"&mobile="+mobile+
								"&encode="+encode+
								"&content=" + URLEncoder.encode(msgContent, "UTF-8");
				
			StringBuffer sb = new StringBuffer(straddr);
			System.out.println("URL:"+sb);
			
			//发送请求
			URL url = new URL(sb.toString());
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			BufferedReader in = new BufferedReader(new InputStreamReader(
					url.openStream()));
			
			//返回结果(sms&stat=100&message=发送成功)
			String inputline = in.readLine();
			logger.info("send sms code response:" + inputline);
			
			String[] rets = inputline.split("&");
			if(rets.length!=3){
				logger.info("send sms return format is error.");
				return false;
			}
			else
			{
				String result =rets[1];
				String status = result.substring(result.indexOf("=")+1, result.length());
				if(status.equalsIgnoreCase("100"))
				{
					return true;
				}
				else if(status.equalsIgnoreCase("101"))
				{
					logger.error("account or password is error.");
					return false;
				}
				else if(status.equalsIgnoreCase("102"))
				{
					logger.error("sms is not enough.");
					return false;
				}
				else if(status.equalsIgnoreCase("117"))
				{
					logger.error("ip address bind is not correct.");
					return false;
				}
				else if(status.equalsIgnoreCase("116"))
				{
					logger.error("disable send the sms.");
					return false;
				}
				else
				{
					logger.error("unknow error.");
					return false;
				}
			}
				
		 }
		 catch(Exception ex)
		 {
			 logger.error("send sms exception occured:" + ex.getMessage());
			 return false;
		 }
		
	 }
	 
}
