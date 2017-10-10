package com.jumper.hospital.enums;
/**
 * 远程监控状态
 * @author rent
 * @date 2015-09-15
 */
public enum RemoteType {
	常规监护, 实时监护, 院内监护;
	
	public static RemoteType getRemoteType(Integer type){
		RemoteType[] remote = RemoteType.values();
		for(int i = 0;i < remote.length;i++){
			if(remote[i].ordinal() == type){
				return remote[i];
			}
		}
		return null;
	}
	
	public Integer ordinal(RemoteType type){
		return type.ordinal();
	}
}
