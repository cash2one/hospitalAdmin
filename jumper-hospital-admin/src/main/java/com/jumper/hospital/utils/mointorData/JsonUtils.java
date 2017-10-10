package com.jumper.hospital.utils.mointorData;
/**
 * json操作utils类，此类后续对json操作可自己扩展，这里仅存放获取服务器胎心json文件内容方法
 * @author rent
 * @date 2015-11-30
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

public class JsonUtils {

	private static final Logger logger = Logger.getLogger(JsonUtils.class);
	
	/**
	 * 获取服务器json文件内容
	 * @param myurl 传入的URL 仅json
	 * @return json字符串
	 * @throws Exception
	 */
	public static String getJsonFileStr(String myurl) {
		BufferedReader br = null;
		try {
			URL url = new URL(myurl);
			br = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
			String s = "";
			StringBuilder sb = new StringBuilder();
			while ((s = br.readLine()) != null) {
				sb.append(s + "\r\n");
			}
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return String.valueOf("");
		} finally {
			if(br != null){
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static Integer getFetalHeartTestTime(String fileUrl){
		String result = getJsonFileStr(fileUrl);
		logger.info(result);
		/** 小于3是判断至少文件中除[]以外至少存在一个点 **/
		if(StringUtils.isEmpty(result) || result.length() < 3 || result.equals("null")){
			return 0;
		}
		String[] str = result.split(",");
		logger.info(str+"--"+str.length);
		Integer time = (int)Math.floor(str.length / 2);
		return time;
	}
	/**获取测试时长的json String 胎心需特殊处理*/
	public static String getTestJsonLength(int testLength,boolean isHeart){
		StringBuffer buffer = new StringBuffer();
		buffer.append("[");
		for(int i=0;i<testLength;i++){
			if(i == (testLength-1)){
				if(isHeart){
					buffer.append(i/120.0);
				}else{
					buffer.append(i/60.0);
				}
			}else{
				if(isHeart){
					buffer.append(i/120.0+",");
				}else{
					buffer.append(i/60.0+",");
				}
			}
		}
		buffer.append("]");
		return buffer.toString();
	}
	/**将2个String[]合并成json 2个String[]长度必须一致*/
	public static String getMixJson(String[] arrA,String[] arrB){
		StringBuffer buffer = new StringBuffer();
		buffer.append("[");
		for(int i=0;i<arrA.length;i++){
			if(i == (arrA.length-1)){
				buffer.append("["+arrA[i]+","+arrB[i]+"]");
			}else{
				buffer.append("["+arrA[i]+","+arrB[i]+"],");
			}
		}
		buffer.append("]");
		return buffer.toString();
	}
	/**解析胎动数据*/
	public static String getFetalMoveData(String str){
		if(StringUtils.isBlank(str)){
			return null;
		}
		String[] strArrA = str.split(",");
		StringBuffer buffer = new StringBuffer();
		for(int i=0;i<strArrA.length;i++){
			if(i == strArrA.length-1){
				buffer.append(strArrA[i].substring(strArrA[i].indexOf(":")+2, strArrA[i].length()-2));
			}else{
				buffer.append(strArrA[i].substring(strArrA[i].indexOf(":")+2, strArrA[i].length()-2)+",");
			}
		}
		String[] strArrB = buffer.toString().split(",");
		StringBuffer buffer2 = new StringBuffer();
		buffer2.append("[");
		for(int i=0;i<strArrB.length;i++){
			String[] arrC = strArrB[i].split(":");
			if(arrC[0].startsWith("0")){
				arrC[0] = arrC[0].substring(1);
			}
			Integer value = Integer.valueOf(arrC[0])*60+Integer.valueOf(arrC[1]);
			if(i == strArrB.length-1){
				buffer2.append(value);
			}else{
				buffer2.append(value+",");
			}
		}
		buffer2.append("]");
		return buffer2.toString();
	}
	
	
	
	
	public static void main(String[] args) {
		Integer time = getFetalHeartTestTime("http://10.0.3.67:8888/group1/M00/01/31/CgABQ1ZhUrOASFU5AAAAB1iAwF030.json");
		System.out.println(time); 
	}
}
