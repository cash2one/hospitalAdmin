package com.jumper.hospital.service;

import java.util.List;

import com.jumper.hospital.entity.VisiterBabyGuidance;

/**
 * 孕期管理中的指导意见模板
 * @author admin
 * 2016-8-12
 */
public interface PregnantBabyVisitModelService extends BaseService<VisiterBabyGuidance, Integer> {

	
	boolean updateModelById(VisiterBabyGuidance  visitBaby );
	
	
	/**
	 * 查询出非固定的模板
	 * @param Type
	 * @return
	 */
	List<VisiterBabyGuidance>  findModelByType(String type );

	/**
	 * 依据模板类型，记录id
	 * 查询模板内容
	 * @param adviceArr
	 * @return
	 */
	String queryContentById(String[] adviceArr,String adviceType);
}
