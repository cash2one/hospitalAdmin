/**
 * 
 */
package com.jumper.hospital.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jumper.hospital.dao.HospitalDoctorNoticeDao;
import com.jumper.hospital.entity.HospitalDoctorNotice;

/**
 * @author huangcr
 *
 * 2017-7-13
 */
@Repository
public class HospitalDoctorNoticeDaoImpl extends BaseDaoImpl<HospitalDoctorNotice, Integer> implements HospitalDoctorNoticeDao {

	/**
		 * @author huangcr
		 * @date 2017-7-13 上午11:51:24
		 * @Description: TODO(这里用一句话描述这个方法的作用)
		 * @param @return
		 * @throws
		 */ 
	@SuppressWarnings("unchecked")
	@Override
	public List<HospitalDoctorNotice> findAll(Integer status,Integer hospitalId) {
		// TODO Auto-generated method stub
		List<HospitalDoctorNotice> doctorids = null;
		if(status == null){
			doctorids= this.getSession().createQuery("from HospitalDoctorNotice do where do.hospitalId=:hospitalId order by convert_mine(do.name)")
					.setParameter("hospitalId", hospitalId)
					.list();
		}else{
			doctorids= this.getSession().createQuery("from HospitalDoctorNotice do where do.hospitalId=:hospitalId and do.status = :status order by convert_mine(do.name)")
					.setParameter("hospitalId", hospitalId)
					.setParameter("status", status)
					.list();
		}
		return doctorids;
	}

	/**
		 * @author huangcr
		 * @date 2017-7-13 下午1:37:33
		 * @Description: TODO(这里用一句话描述这个方法的作用)
		 * @param 
		 * @throws
		 */ 
	@Override
	public void deleteDoctor(Integer status,Integer hospitalId) {
		// TODO Auto-generated method stub
		this.getSession().createQuery("Delete FROM HospitalDoctorNotice do Where do.hospitalId=:hospitalId and do.status = :status")
		.setParameter("hospitalId", hospitalId)
		.setParameter("status", status)
		.executeUpdate();
	}
	
	@Override
	public void deleteDoctorByPhone(String username,Integer hospitalId) {
		// TODO Auto-generated method stub
		this.getSession().createQuery("Delete FROM HospitalDoctorNotice do Where do.hospitalId=:hospitalId and do.username = :username")
		.setParameter("hospitalId", hospitalId)
		.setParameter("username", username)
		.executeUpdate();
	}

	/**
		 * @author huangcr
		 * @date 2017-7-14 上午10:43:44
		 * @Description: TODO(这里用一句话描述这个方法的作用)
		 * @param @param hospitalId
		 * @throws
		 */ 
	@Override
	public void updateStatus(Integer status,Integer hospitalId) {
		// TODO Auto-generated method stub
		this.getSession().createQuery("Update HospitalDoctorNotice do set do.status = :status where do.hospitalId = :hospitalId")
			.setParameter("status", status)
			.setParameter("hospitalId", hospitalId)
			.executeUpdate();
	}

	/**
		 * @author huangcr
		 * @date 2017-7-26 下午2:20:14
		 * @Description: TODO(这里用一句话描述这个方法的作用)
		 * @param @param username
		 * @param @param hospitalId
		 * @param @return
		 * @throws
		 */ 
	@SuppressWarnings("unchecked")
	@Override
	public HospitalDoctorNotice getHospitalDoctorNotice(String username,
			Integer hospitalId) {
		// TODO Auto-generated method stub
		List<HospitalDoctorNotice> doctorids = null;
		doctorids =  this.getSession().createQuery("from HospitalDoctorNotice do where do.hospitalId=:hospitalId and do.username=:username")
		.setParameter("hospitalId", hospitalId)
		.setParameter("username", username)
		.list();
		if(doctorids != null && doctorids.size()>0){
			return doctorids.get(0);
		}
		return null;
	}

	/**
		 * @author huangcr
		 * @date 2017-7-26 下午2:31:51
		 * @Description: TODO(这里用一句话描述这个方法的作用)
		 * @param @param username
		 * @param @param status
		 * @param @param hospitalId
		 * @throws
		 */ 
	@Override
	public void updateStatusByUsername(String username, Integer status,
			Integer hospitalId) {
		// TODO Auto-generated method stub
		this.getSession().createQuery("Update HospitalDoctorNotice do set do.status = :status where do.hospitalId = :hospitalId and do.username=:username")
		.setParameter("status", status)
		.setParameter("hospitalId", hospitalId)
		.setParameter("username", username)
		.executeUpdate();
	}

	/**
		 * @author huangcr
		 * @date 2017-7-26 下午2:53:37
		 * @Description: TODO(这里用一句话描述这个方法的作用)
		 * @param @param status
		 * @param @param newStatus
		 * @param @param hospitalId
		 * @throws
		 */ 
	@Override
	public void updateStatusByNewStatus(Integer status, Integer newStatus,
			Integer hospitalId) {
		// TODO Auto-generated method stub
		this.getSession().createQuery("Update HospitalDoctorNotice do set do.status = :newStatus where do.hospitalId = :hospitalId and do.status=:status")
		.setParameter("status", status)
		.setParameter("hospitalId", hospitalId)
		.setParameter("newStatus", newStatus)
		.executeUpdate();
		
	}

	/**
		 * @author huangcr
		 * @date 2017-7-26 下午3:10:14
		 * @Description: TODO(这里用一句话描述这个方法的作用)
		 * @param @param status
		 * @param @param hospitalId
		 * @param @return
		 * @throws
		 */ 
	@SuppressWarnings("unchecked")
	@Override
	public List<HospitalDoctorNotice> findAllisnotZero(Integer status,
			Integer hospitalId) {
		// TODO Auto-generated method stub
		List<HospitalDoctorNotice> doctorids = null;
		doctorids = this
				.getSession()
				.createQuery(
						"from HospitalDoctorNotice do where do.hospitalId=:hospitalId and do.status != :status order by convert_mine(do.name)")
				.setParameter("hospitalId", hospitalId)
				.setParameter("status", status).list();
		return doctorids;
	}
	

}
