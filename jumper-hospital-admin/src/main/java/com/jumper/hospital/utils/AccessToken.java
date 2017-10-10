package com.jumper.hospital.utils;

/**
 * 健海约定的较验参数
 */
public class AccessToken {
	String objectId;
	long time;
	String md5;
	int userCate;
	public AccessToken() {
		super();
	}
	public AccessToken(String objectId, long time, String md5, int userCate) {
		super();
		this.objectId = objectId;
		this.time = time;
		this.md5 = md5;
		this.userCate = userCate;
	}
	public String getObjectId() {
		return objectId;
	}
	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
	public String getMd5() {
		return md5;
	}
	public void setMd5(String md5) {
		this.md5 = md5;
	}
	public int getUserCate() {
		return userCate;
	}
	public void setUserCate(int userCate) {
		this.userCate = userCate;
	}
	@Override
	public String toString() {
		return 	"objectId="+objectId+",time="+time+",md5="+md5+",userCate="+userCate;
	}
	
    
}
