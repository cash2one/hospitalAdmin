/*
 * @文件名： FamilyVersionServiceImpl.java
 * @创建人: aaron
 * @创建时间: 2016-2-24
 * @包名： com.jumper.hospital.service.impl
 * @版本： 1.0
 * 版权所有 © 深圳京柏医疗
 * 描述:
*/
package com.jumper.hospital.service.impl;

import java.util.List;
import java.util.Map;

import javax.persistence.Entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jumper.hospital.dao.BaseDao;
import com.jumper.hospital.dao.FamilyQuestionnaireResultsDao;
import com.jumper.hospital.entity.FamilyQuestionnaireResults;
import com.jumper.hospital.service.FamilyQuestionnaireResultsService;

/**
 * 类名称：FamilyVersionServiceImpl
 * 类描述：
 * 创建人：aaron
 * 创建时间：2016-2-24 下午4:54:11
 * 修改人：aaron
 * 修改时间：2016-2-24 下午4:54:11
 * 修改备注：
 * @version  1.0
 */
@Entity
@Service
public class FamilyQuestionnaireResultsServiceImpl extends BaseServiceImpl<FamilyQuestionnaireResults, Integer> implements FamilyQuestionnaireResultsService
{
	@Autowired
	FamilyQuestionnaireResultsDao familyQuestionnaireResultsDao;

	@Override
	public BaseDao<FamilyQuestionnaireResults, Integer> getBaseDAO() {
		 
		return familyQuestionnaireResultsDao;
	}

	@Override
	public List<Map<String,Object>> getQuestionReport(Integer userid) {
		return familyQuestionnaireResultsDao.getQuestionReport(userid);
	}
	 
}
