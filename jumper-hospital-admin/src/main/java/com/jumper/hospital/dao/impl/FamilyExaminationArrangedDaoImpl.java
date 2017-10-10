/*
 * @文件名： FamilyDoctorinfoDaoImpl.java
 * @创建人: aaron
 * @创建时间: 2016-2-17
 * @包名： com.jumper.hospital.dao.impl
 * @版本： 1.0
 * 版权所有 © 深圳京柏医疗
 * 描述:
*/
package com.jumper.hospital.dao.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.jumper.hospital.dao.FamilyExaminationArrangedDao;
import com.jumper.hospital.entity.FamilyExaminationArranged;
import com.jumper.hospital.vo.familyDoctor.VOFamilyExaminationArranged;

/**
 * 类名称：FamilyDoctorinfoDaoImpl
 * 类描述：
 * 创建人：aaron
 * 创建时间：2016-2-17 下午1:44:48
 * 修改人：aaron
 * 修改时间：2016-2-17 下午1:44:48
 * 修改备注：
 * @version  1.0
 */
@Repository
public class FamilyExaminationArrangedDaoImpl extends BaseDaoImpl<FamilyExaminationArranged, Integer> implements FamilyExaminationArrangedDao
{

	@SuppressWarnings("unchecked")
	@Override
	public List<VOFamilyExaminationArranged> getFamilyExaminationArrangedsByUserInfoId(
			Integer id, Short state) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
		String currentTime = df.format(new Date());
		return this.getSession().createQuery("select new  com.jumper.hospital.vo.familyDoctor.VOFamilyExaminationArranged(" +
				"fea.id,fea.userid,fea.doctorid,fea.homeVisitsDate,fea.state)" +
				" from  FamilyExaminationArranged fea where fea.userid=? and fea.state=? and fea.homeVisitsDate='"+currentTime+"'")
				.setParameter(0, id).setParameter(1, state).list();
	}

	@Override
	public void saveFamilyExaminationArranged(
			FamilyExaminationArranged examinationArranged) {
		this.getSession().save(examinationArranged);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<VOFamilyExaminationArranged> getFamilyExaminationArrangedsByUserInfoId4State(
			Integer id, short s) {
		return this.getSession().createQuery("from FamilyExaminationArranged fea where fea.userid=? and fea.state=?").setParameter(0, id).setParameter(1, s).list();
	}
// 通常情况下state的状态市未完成
	@SuppressWarnings("unchecked")
	@Override
	public FamilyExaminationArranged getRecentlyFamilyExaminationArrangedByUserInfoId(
			Integer userid, Short state) {
		System.out.println(userid);
		List<FamilyExaminationArranged> list = this.getSession().createQuery("from FamilyExaminationArranged fea where fea.userid=? and fea.state=? order by fea.homeVisitsDate")
		.setParameter(0, userid).setParameter(1, state).list();
		
		return null==list||0==list.size()?null:list.get(0);
	}

}
















