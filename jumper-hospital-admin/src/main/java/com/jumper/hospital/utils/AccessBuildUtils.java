package com.jumper.hospital.utils;





/**
 * 
 * @author xunianchong
 *
 */
public class AccessBuildUtils {
	/**
	 * 过期时间120秒
	 */
	static final long EXPIRED_TIME = 1000*60*60*24;
	
	/**
	 * 是否有访问ps的权限
	 * 主要指其它系统
	 * 指其它的应用访问我们的应用
	 */
	public static boolean hasPri(String accessKey,String accessToken, String accessTime){
		boolean result = HashUtils.encryptMD5(accessTime+accessKey).equals(accessToken);
		if(result){
			result=System.currentTimeMillis() - Long.valueOf(accessTime) < EXPIRED_TIME;
		}
		return result;
	}
	/**
	 *  生成系统的访问token 
	 *  主要是我们的应用访问别的应用 ，通过http访问
	 */
	public static AccessToken createHttpAccessToken(String accessKey,String objectId,int userCate){
		long time = System.currentTimeMillis();
		AccessToken accessToken = new AccessToken();
		accessToken.setTime(time);
		accessToken.setObjectId(objectId);
		accessToken.setUserCate(userCate);
		String md5 = 	HashUtils.encryptMD5(time+accessKey);
		accessToken.setMd5(md5);
		return accessToken;
	}
	/**
	 *  主要是我们的应用别的应用token的生成
	 *  web url token的生成
	 */
	public static String createWebAccessToken(String accessKey,String objectId,int userCate){
		long time = System.currentTimeMillis();
		AccessToken accessToken = new AccessToken();
		accessToken.setTime(time);
		String md5 = 	HashUtils.encryptMD5(time+accessKey);
		accessToken.setMd5(md5);
		return HashUtils.encryptMD5(time+accessKey)+"_"+objectId+"_"+time+"_"+userCate;
	}
}
