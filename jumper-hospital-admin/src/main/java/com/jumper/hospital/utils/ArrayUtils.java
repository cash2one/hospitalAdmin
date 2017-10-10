package com.jumper.hospital.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

/**
 * 数组处理工具类
 * @author rent
 * @date 2015-09-24
 */
public class ArrayUtils {

	/**
	 * 将枚举类型的List转换为逗号分隔开的字符串
	 * @param list
	 * @return String
	 */
	@SuppressWarnings("rawtypes")
	public static String listToString(List list) {
	    StringBuilder sb = new StringBuilder();
	    if (list != null && list.size() > 0) {
	        for (int i = 0; i < list.size(); i++) {
	            if (i < list.size() - 1) {
	                sb.append(list.get(i).toString() + "、");
	            } else {
	                sb.append(list.get(i).toString());
	            }
	        }
	    }
	    return sb.toString();
	}
	
	@SuppressWarnings("rawtypes")
	public static String listToString(List list, String seq){
        if (list==null) {
            return null;
        }
        StringBuilder result=new StringBuilder();
        boolean flag=false;
        for (Object obj : list) {
            if (flag) {
                result.append(seq);
            }else {
                flag=true;
            }
            result.append(obj);
        }
        return result.toString();
    }
	
	/**
	 * 判断list是否为空
	 * @param list 判断参数
	 * @return boolean true | false
	 */
	@SuppressWarnings("rawtypes")
	public static boolean isNotEmpty(List list){
		if(list == null || list.size() < 1){
			return false;
		}
		return true;
	}
	
	/**
	 * 判断list是否为空
	 * @param list 判断参数
	 * @return boolean true | false
	 */
	@SuppressWarnings("rawtypes")
	public static boolean isEmpty(List list){
		if(list != null && list.size() > 0){
			return false;
		}
		return true;
	}
	
	/**
	 * 判断map是否为空
	 * @param map
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static boolean isEmpty(Map map){
		if(map == null || map.size() < 1){
			return true;
		}
		return false;
	}
	
	/**
	 * 判断map是否为空
	 * @param map
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static boolean isNotEmpty(Map map){
		if(map != null && map.size() > 0){
			return true;
		}
		return false;
	}
	/**
	 * 读取添加宫缩医院的配置
	 * @param hospitalId
	 * @return
	 */
	public static boolean checkIsTOCOHospital(Integer hospitalId){
		String path = ArrayUtils.class.getClassLoader().getResource("").getPath()+"TOCO.txt";
		BufferedReader reader = null;
		try {
			File file = new File(path);
			if(file != null){
				reader = new BufferedReader(new FileReader(file));
				String line = null;
				StringBuilder sb = new StringBuilder();
				while ((line = reader.readLine()) != null) {
					sb.append(line);
				}
				String result = sb.toString();
				System.out.println("配置文件内容："+result);
				if(StringUtils.isNotEmpty(result)){
					List<String> toco = Arrays.asList(result.split(","));
					if(toco.contains(String.valueOf(hospitalId))){
						return true;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(reader != null){
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return false;
	}
	
	/**
	 * 读取添加胎心监护线上支付服务医院的配置
	 * @param hospitalId 这里是医院的ID，记住是医院的ID，HospitalInfo表
	 * @return boolean true : 包含(需要跳转到新的页面) false : 不含(跳到原有界面)
	 */
	public static boolean checkIsLINEHospital(Integer hospitalId){
		String path = ArrayUtils.class.getClassLoader().getResource("").getPath()+"LINE.txt";
		BufferedReader reader = null;
		try {
			File file = new File(path);
			if(file != null){
				reader = new BufferedReader(new FileReader(file));
				String line = null;
				StringBuilder sb = new StringBuilder();
				while ((line = reader.readLine()) != null) {
					sb.append(line);
				}
				String result = sb.toString();
				System.out.println("配置文件内容："+result);
				if(StringUtils.isNotEmpty(result)){
					List<String> lineArray = Arrays.asList(result.split(","));
					if(lineArray.contains(String.valueOf(hospitalId))){
						return true;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(reader != null){
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return false;
	}
}
