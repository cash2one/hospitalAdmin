package com.jumper.hospital.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.jumper.hospital.dao.HospitalDoctorInfoDao;
import com.jumper.hospital.entity.HospitalDoctorInfo;
@Repository
public class HospitalDoctorInfoDaoImpl extends BaseDaoImpl<HospitalDoctorInfo, Integer> implements HospitalDoctorInfoDao{

	/**
	 * 
	 *查询医生信息
	 */
	@Override
	public HospitalDoctorInfo selectDoctorById(Integer id) {
		Criteria c = createCriteria();
		c.add(Restrictions.eq("id", id));
		if(c.list().size()>0){
			return (HospitalDoctorInfo) c.list().get(0);
		}else{
			return null;
		}
	}

	@Override
	public HospitalDoctorInfo selectDoctorByPhone(Integer hospitalId,String phone) {
		Criteria c = createCriteria();
		c.add(Restrictions.eq("phone", phone));
		c.add(Restrictions.eq("hospitalId", hospitalId));
		if(c.list().size()>0){
			return (HospitalDoctorInfo) c.list().get(0);
		}else{
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Integer> findDoctorIdsByDoctorName(Integer id,
			String searchDoctor) {
		List<Integer> doctors=new ArrayList<Integer>();
		Criteria c = createCriteria();
		c.add(Restrictions.eq("hospitalId", id));
		c.add(Restrictions.like("name", searchDoctor, MatchMode.ANYWHERE));
		if(c.list().size()>0){
			List<HospitalDoctorInfo> doctorids=(List<HospitalDoctorInfo>)c.list();
			for(HospitalDoctorInfo doctor:doctorids){
				doctors.add(doctor.getId());
			}
			return doctors;
		}else{
			return null;
		}
	}

	/**
		 * @author huangcr
		 * @date 2017-7-12 下午3:10:29
		 * @Description: TODO(这里用一句话描述这个方法的作用)
		 * @param @return
		 * @throws
		 */ 
	@SuppressWarnings("unchecked")
	@Override
	public List<HospitalDoctorInfo> findAllDoctor(Integer hospitalId) {
		// TODO Auto-generated method stub
		List<HospitalDoctorInfo> doctorids= this.getSession().createQuery("from HospitalDoctorInfo do where do.hospitalId=:hospitalId and do.phone != '' order by convert_mine(do.name)")
				.setParameter("hospitalId", hospitalId)
				.list();
		return doctorids;
		
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<HospitalDoctorInfo> findDoctorByName(String name,Integer hospitalId){
		List<HospitalDoctorInfo> list = this.getSession().createQuery("from HospitalDoctorInfo do where do.hospitalId=:hospitalId and do.phone != '' and do.name like :name order by convert_mine(do.name)")
				.setParameter("hospitalId", hospitalId)
				.setParameter("name", "%"+name+"%").list();
				return null==list|| list.size()<=0?null:list;
	}

}
