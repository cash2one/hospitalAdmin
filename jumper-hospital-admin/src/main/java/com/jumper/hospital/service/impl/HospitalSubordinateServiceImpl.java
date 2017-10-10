package com.jumper.hospital.service.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jumper.hospital.base.Page;
import com.jumper.hospital.dao.BaseDao;
import com.jumper.hospital.dao.HospitalInfoDao;
import com.jumper.hospital.dao.HospitalSubordinateDao;
import com.jumper.hospital.entity.HospitalInfo;
import com.jumper.hospital.entity.HospitalSubordinate;
import com.jumper.hospital.service.HospitalSubordinateService;
/**
 * 医院从属关系 业务实现类
 * @author tanj 谭隽
 *
 */
@Service
public class HospitalSubordinateServiceImpl extends BaseServiceImpl<HospitalSubordinate, Integer> implements HospitalSubordinateService {

	/**
	 * 装载从属表数据访问层
	 */
	@Autowired
	HospitalSubordinateDao hospitalSubordinateDao;
	/**
	 * 装载医院数据访问层
	 */
	@Autowired
	HospitalInfoDao hospitalInfoDao;

	@Override
	public BaseDao<HospitalSubordinate, Integer> getBaseDAO() {
		return hospitalSubordinateDao;
	}
	
	/**
	 * 根据上级医院 获取从属医院列表 分页
	 * @param page
	 * @param hospitalId
	 */
	@Override
	public Page<HospitalSubordinate> getHospitalSubordinateList(Integer hospitalId, Page<HospitalSubordinate> page) {
		return hospitalSubordinateDao.getHospitalSubordinateList(hospitalId, page);
	}

	/**
	 * 根据上级医院 获取未从属医院列表
	 * @param hospitalId
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<HospitalInfo> getNotSubordinates(HospitalInfo hospitalInfo) {
		List<Integer> isSubordinateList = hospitalSubordinateDao.findSubordinateList(hospitalInfo.getId());
		Criteria c1 = hospitalInfoDao.createCriteria();
		if(isSubordinateList!=null && isSubordinateList.size()>0){
			c1.add(Restrictions.not(Restrictions.in("id", isSubordinateList)));
		}
		c1.add(Restrictions.ilike("name",hospitalInfo.getName().substring(0, 2),MatchMode.START));//前两个字符的模糊查询
		c1.add(Restrictions.not(Restrictions.eq("id", hospitalInfo.getId())));
		return c1.list();
	}
	
	
	

}
