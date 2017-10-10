package com.jumper.hospital.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 星期时间枚举
 * @author rent
 * @date 2015-09-21
 */
public enum Week {
	周一,周二,周三,周四,周五,周六,周日;
	
	/**
	 * enum to map用户前端jstl
	 * @return
	 */
	public static Map<Integer, String> getMapResult(){
		Map<Integer, String> map = new HashMap<Integer, String>();
		for(Week week : Week.values()){
			map.put(week.ordinal(), week.toString());
		}
		return map;
	}
	
	public static Week getWeekByInteger(Integer week){
		for(Week weeks : Week.values()){
			if(weeks.ordinal() == week){
				return weeks;
			}
		}
		return null;
	}
}
