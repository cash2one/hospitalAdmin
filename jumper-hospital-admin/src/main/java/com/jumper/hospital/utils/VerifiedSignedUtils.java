package com.jumper.hospital.utils;

/**
 * 验证签名工具类
 * @author win
 *
 */
public class VerifiedSignedUtils {

	public static boolean verified(String method,String params,String signed)
	{
		String api_key=Const.SIGNED_KEYS;
		String combined_str = api_key+"method"+method+"params"+params+api_key;
		String tmp_signed = MD5EncryptUtils.getMd5Value(combined_str);
		if(tmp_signed.equals(signed))
		{
			return true;
		}
		return false;
	}
}
