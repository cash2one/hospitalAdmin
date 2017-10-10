package com.jumper.hospital.dao.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.jumper.hospital.dao.HospitalVisitDao;
import com.jumper.hospital.entity.Admin;
import com.jumper.hospital.entity.HospitalInfo;
import com.jumper.hospital.entity.HospitalService;

@Repository
public class HospitalVisitDaoImpl extends BaseDaoImpl<HospitalService, Integer> implements HospitalVisitDao {

	@Override
	public HospitalService getServiceInfo(Integer id) {
		Criteria c = createCriteria();
		c.add(Restrictions.idEq(id));
		if(c.list()!=null&&c.list().size()>0){
			return (HospitalService) c.list().get(0);
		}
		return null;
	}

	@Override
	public void addVisitInfo(double money, int week_low, int week_high,
			String beginTime, String end_Time, Admin admin,
			HospitalInfo hospitalInfo) {
		HospitalService hospitalService = new HospitalService();
		//医院状态设置为开通
		hospitalInfo.setIsConsultant(1);
		hospitalService.setHospitalInfo(hospitalInfo);
		hospitalService.setServicecost(money);
		hospitalService.setAdmin(admin);
		hospitalService.setAddTime(new Date());
		hospitalService.setUpdateTime(new Date());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try {
			hospitalService.setBeginTime(sdf.parse("1970-01-01 " + beginTime));
			hospitalService.setEndTime(sdf.parse("1970-01-01 " + end_Time));
			hospitalService.setWeeklow(week_low);
			hospitalService.setWeekhigh(week_high);
			//服务状态设置为开通
			hospitalService.setStatus(1);
			try {
				save(hospitalService);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	@Override
	public HospitalService findHospitalService(Integer id) {
		Criteria c = createCriteria();
		c.add(Restrictions.eq("hospitalInfo.id", id));
		if(c.list().size()<=0){
			return null;
		}else{
			return (HospitalService) c.list().get(0);
		}
		
	}
	
}
