package com.jumper.hospital.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式公共类
 * @author rent
 * @date 2016-02-26
 */
public class RegexUtils {

	/**
	 * 替换第三方视频分享的显示宽高
	 * @param source 通用原代码
	 * @return
	 */
	public static String replaceVideoWidthAndHeight(String source){
		String qq = "qq";//腾讯视频
		String youku = "youku";//优酷视频
		String tudou = "tudou";//土豆视频
		String iqiyi = "iqiyi";//爱奇艺视频
		
		String regexWidth = "";
		String regexHeight = "";
		String replaceWidth = "";
		String replaceHeight = "";
		
		if(source.indexOf(qq) != -1){
			regexWidth = "width=\"(.*?)\"";
			regexHeight = "height=\"(.*?)\"";
			replaceWidth = "width=\"100%\"";
			replaceHeight = "height=\"300\"";
		}else if(source.indexOf(youku) != -1){
			regexWidth = "width=[0-9]*";
			regexHeight = "height=[0-9]*";
			replaceWidth = "width=100%";
			replaceHeight = "height=300";
		}else if(source.indexOf(tudou) != -1){
			regexWidth = "width:(.*?)px";
			regexHeight = "height:(.*?)px";
			replaceWidth = "width:100%";
			replaceHeight = "height:300px";
		}else if(source.indexOf(iqiyi) != -1){
			regexWidth = "width=\"(.*?)\"";
			regexHeight = "height=\"(.*?)\"";
			replaceWidth = "width=\"100%\"";
			replaceHeight = "height=\"300\"";
		}else{
			return null;
		}
		
		Pattern width = Pattern.compile(regexWidth, Pattern.CASE_INSENSITIVE);
	    Pattern height = Pattern.compile(regexHeight, Pattern.CASE_INSENSITIVE);
	    Matcher mWidth = width.matcher(source);
	    if(mWidth.find()){
	    	String widthReplace = mWidth.replaceAll(replaceWidth);
	    	Matcher mHeight = height.matcher(widthReplace);
	    	if(mHeight.find()){
	    		String result = mHeight.replaceAll(replaceHeight);
	    		return result;
	    	}
	    }
		return null;
	}
}
