package com.jumper.hospital.dao.impl;
/**
 * 医院屏幕占用情况Dao实现类
 * @author rent
 * @date 2015-10-10
 */
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.jumper.hospital.dao.MonitorSmallScreenDao;
import com.jumper.hospital.entity.MonitorSmallScreen;
import com.jumper.hospital.utils.TimeUtils;

@Repository
public class MonitorSmallScreenDaoImpl extends BaseDaoImpl<MonitorSmallScreen, Integer> implements MonitorSmallScreenDao {

	private static final Logger logger = Logger.getLogger(MonitorSmallScreenDaoImpl.class);
	
	/**
	 * 获取医院屏幕使用情况
	 * remark:此处如果医院ID在数据库中不唯一，查询会失败，如果此后医院可以开通多个远程监控端，此方法会有异常
	 */
	public MonitorSmallScreen findScreenStateByHospitalId(Integer hospitalId) {
		try {
			Criteria c = createCriteria();
			c.add(Restrictions.eq("hospitalID", String.valueOf(hospitalId)));
			MonitorSmallScreen screen = (MonitorSmallScreen) c.uniqueResult();
			if(screen != null){
				return screen;
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(TimeUtils.getCurrentTime("")+"--通过医院查询医院八块屏幕使用状态异常!异常原因:"+e.getMessage());
			return null;
		}
	}

}
