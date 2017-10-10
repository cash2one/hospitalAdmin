/*
 * @文件名： FamilyUserinfoDaoImpl.java
 * @创建人: aaron
 * @创建时间: 2016-2-17
 * @包名： com.jumper.hospital.dao.impl
 * @版本： 1.0
 * 版权所有 © 深圳京柏医疗
 * 描述:
*/
package com.jumper.hospital.dao.impl;

import java.util.List;
import java.util.Map;

import javax.persistence.Entity;

import org.springframework.stereotype.Repository;

import com.jumper.hospital.dao.FamilyQuestionnaireResultsDao;
import com.jumper.hospital.entity.FamilyQuestionnaireResults;

/**
 * 类名称：FamilyUserinfoDaoImpl
 * 类描述：
 * 创建人：aaron
 * 创建时间：2016-2-17 下午1:33:46
 * 修改人：aaron
 * 修改时间：2016-2-17 下午1:33:46
 * 修改备注：
 * @version  1.0
 */
@Entity
@Repository
public class FamilyQuestionnaireResultsDaoImpl extends BaseDaoImpl<FamilyQuestionnaireResults, Integer> implements FamilyQuestionnaireResultsDao
{

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String,Object>> getQuestionReport(Integer userid) {
		return this.getSession().createQuery("select new map(date_format(ff.addTime,'%Y-%m-%d %H:%i') as addTime,ff.resultUrl as resultUrl) from FamilyQuestionnaireResults ff where ff.userId=:userId").setParameter("userId", userid).list();
	}
	 
}