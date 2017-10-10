package com.jumper.hospital.dao.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.NoResultException;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.jumper.hospital.base.Page;
import com.jumper.hospital.dao.AdminDao;
import com.jumper.hospital.entity.Admin;


/**
 * Dao实现类 - 管理员
 * @author rent
 * @date 2015-10-16
 */
@Repository
public class AdminDaoImpl extends BaseDaoImpl<Admin, Integer> implements AdminDao {

	public boolean usernameExists(String username) {
		if (username == null) {
			return false;
		}
		Criteria c = createCriteria();
		c.add(Restrictions.eq("username", username).ignoreCase());
		c.setProjection(Projections.rowCount());
		long count = (Long) c.uniqueResult();
		return count > 0;
	}

	public Admin findByUsername(String username) {
		if (username == null) {
			return null;
		}
		try {
			return findUniqueBy("username", username);
		} catch (NoResultException e) {
			return null;
		}
	}

	public Page<Admin> getAdminList(Integer hospitalId, Integer type, Page<Admin> page, String username,Integer majorid,Integer titleid){
		Criteria c = createCriteria();
		if(type == 0){
			//c.add(Restrictions.eq("isFather", true));
			c.add(Restrictions.isNotNull("hospitalInfo"));
		}else{
			c.add(Restrictions.eq("isFather", false));
			c.add(Restrictions.eq("hospitalInfo.id", hospitalId));
		}
		if(StringUtils.isNotEmpty(username)){
			c.add(Restrictions.or(Restrictions.like("username", username, MatchMode.START), Restrictions.like("name", username, MatchMode.START)));
		}
		if(majorid!=null){
			c.add(Restrictions.eq("hospitalMajorInfo.id", majorid));
		}
		if(titleid!=null){
			c.add(Restrictions.eq("hospitalDoctorTitle.id", titleid));
		}
		c.addOrder(Order.desc("loginDate"));
		return findPageByCriteria(page, c);
	}

	@Override
	public Boolean checkHospitalHasOpen(Integer hospitalId) {
		try {
			Criteria c = createCriteria();
			c.add(Restrictions.eq("hospitalInfo.id", hospitalId));
			c.add(Restrictions.eq("isFather", true));
			c.setProjection(Projections.rowCount());
			long count = (Long) c.uniqueResult();
			return count > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return true;
		}
	}

    /**
     * @see com.jumper.hospital.dao.AdminDao#getidsByHospitalID(java.lang.Integer)
     */
    @SuppressWarnings("unchecked")
	@Override
    public List<Integer> getidsByHospitalID(Integer id)
    {
        Criteria c = createCriteria();
        c.add(Restrictions.eq("hospitalInfo.id", id));
        List<Integer> ids = null;
        List<Admin> list = c.list();
        if(CollectionUtils.isNotEmpty(list)){
            ids = new ArrayList<Integer>(list.size());
            for (Admin admin : list)
            {
                ids.add(admin.getId());
            }
            Collections.sort(ids);
        }
        return ids;
    }

	/**
	 * 通过用户名id查询管理员
	 * @param adminId 用户id
	 * @return Admin管理员对象
	 */
	@Override
	public Admin findByAdminId(Integer adminId) {
		if (null == adminId) {
			return null;
		}
		try {
			return findUniqueBy("id", adminId);
		} catch (NoResultException e) {
			return null;
		}
	}
	
	/**
	 * 根据当前账号
	 */
	@Override
	public Admin matchCurrentHospitalAdmin(Integer id, String searchDoctor) {

		Criteria c = createCriteria();
		c.add(Restrictions.eq("hospitalInfo.id", id));
		c.add(Restrictions.eq("isFather", true));
		c.add(Restrictions.like("name", searchDoctor, MatchMode.ANYWHERE));
		if(c.list().size()>0){
			return (Admin)c.list().get(0);
		}
		return null;
	}

	@Override
	public Admin CurrentHospitalAdmin(Integer hospitalId) {
		Criteria c = createCriteria();
		c.add(Restrictions.eq("hospitalInfo.id", hospitalId));
		c.add(Restrictions.eq("isFather", true));
		if(c.list().size()>0){
			return (Admin)c.list().get(0);
		}
		return null;
	}

	/**
		 * @author huangcr
		 * @date 2017-7-25 上午11:18:03
		 * @Description: TODO(这里用一句话描述这个方法的作用)
		 * @param @param hospitalId
		 * @param @return
		 * @throws
		 */ 
	@SuppressWarnings("unchecked")
	@Override
	public List<Admin> findAllDoctor(Integer hospitalId) {
		// TODO Auto-generated method stub
		List<Admin> doctorids = this
				.getSession()
				.createQuery(
						"from Admin m where m.isFather = 0 and m.hospitalInfo.id=:hospitalId and m.mobile != '' order by convert_mine(m.name)")
				.setParameter("hospitalId", hospitalId).list();
		return doctorids;
	}

	/**
		 * @author huangcr
		 * @date 2017-7-25 上午11:18:03
		 * @Description: TODO(这里用一句话描述这个方法的作用)
		 * @param @param name
		 * @param @param hospitalId
		 * @param @return
		 * @throws
		 */ 
	@SuppressWarnings("unchecked")
	@Override
	public List<Admin> findDoctorByName(String name, Integer hospitalId) {
		// TODO Auto-generated method stub
		List<Admin> list = this
				.getSession()
				.createQuery(
						"from Admin m where m.isFather = 0 and m.hospitalInfo.id=:hospitalId and m.mobile != '' and m.name like :name order by convert_mine(m.name)")
				.setParameter("hospitalId", hospitalId)
				.setParameter("name", "%" + name + "%").list();
		return null == list || list.size() <= 0 ? null : list;
	}
}