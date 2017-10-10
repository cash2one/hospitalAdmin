package com.jumper.hospital.utils;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * hash算法
 * <pre>
 * @Description: HashUtils
 * @author  xunianchong
 * @email xnc@dashuju.com
 * @date: 2015年11月2日
 * @version 1.0
 * </pre>
 */
public class HashUtils {
	public static String encryptMD5(String  s) {
		return getByteMD5(s.getBytes());
	}
	/**
	 * 得到字节数组的MD5值
	 * 
	 * @param btInput字节数组
	 * @return
	 */
	public static String getByteMD5(byte[] btInput) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			// 获得MD5摘要算法的 MessageDigest 对象
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			// 使用指定的字节更新摘要
			mdInst.update(btInput);
			// 获得密文
			byte[] md = mdInst.digest();
			// 把密文转换成十六进制的字符串形式
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			e.printStackTrace();

		}
		return null;
	}

	/**
	 * 得到文件MD5值
	 * 
	 * @param path
	 *            文件路径
	 * @return
	 */
	public static String encryptFileMD5(String path) {
		File file = new File(path);
		return encryptFileMD5(file);
	}

	public static String encryptFileMD5(File file) {
		MessageDigest digest = null;
		FileInputStream in = null;
		byte buffer[] = new byte[1024];
		int len;
		try {
			digest = MessageDigest.getInstance("MD5");
			in = new FileInputStream(file);
			while ((len = in.read(buffer, 0, 1024)) != -1) {
				digest.update(buffer, 0, len);
			}
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		BigInteger bigInt = new BigInteger(1, digest.digest());
		return bigInt.toString(16);
	}
	
	

}
