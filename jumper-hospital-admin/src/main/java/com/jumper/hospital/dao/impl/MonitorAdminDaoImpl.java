package com.jumper.hospital.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.jumper.hospital.dao.MonitorAdminDao;
import com.jumper.hospital.entity.MonitorAdmin;

@Repository
public class MonitorAdminDaoImpl extends BaseDaoImpl<MonitorAdmin, Integer> implements MonitorAdminDao {

	/**
	 * 查询是否存在
	 */
	public boolean existMonitorAdmin(String mobile) {
		Criteria c=this.createCriteria();
		try {
			c.add(Restrictions.eq("mobile",mobile));
			
			MonitorAdmin result = (MonitorAdmin)c.uniqueResult();
			if(result==null){
				return false;
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
