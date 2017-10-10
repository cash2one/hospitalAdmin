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

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jumper.hospital.dao.NetWorkManageDao;
import com.jumper.hospital.entity.Admin;

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
public class NetWorkManageDaoImpl  extends BaseDaoImpl<Admin, Integer>  implements NetWorkManageDao 
{



	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getHospitalDoctorTitleList(Integer hospitalId) {
		//String sql="select hdt.id,hdt.title,hdt.name  from HospitalDoctorInfo hdi, HospitalDoctorTitle hdt where hdi.titleId = hdt.id and hdi.hospitalId = " + hospitalId + " GROUP BY hdt.id";
		  String sql="select hdt.id,hdt.title,hdt.name  from  HospitalDoctorTitle hdt";
		List<Object> list = this.getSession().createQuery(sql).list();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public  List<Integer> getDoctorMajorIdByMajorInfoId(Integer id) {
		String sql="select ht.majorId from HospitalMajorInfo ht where ht.id="+id;
		List<Integer> doctorMajorId = this.getSession().createQuery(sql).list();
		return doctorMajorId;
	}

}
















