package com.jumper.hospital.dao.impl;
/**
 * 远程监控项目Dao实现类
 * @author rent
 * @date 2015-09-30
 */
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.jumper.hospital.dao.MonitorProjectDao;
import com.jumper.hospital.entity.MonitorProject;
import com.jumper.hospital.utils.TimeUtils;

@Repository
public class MonitorProjectDaoImpl extends BaseDaoImpl<MonitorProject, Integer> implements MonitorProjectDao {

	private static final Logger logger = Logger.getLogger(MonitorProjectDaoImpl.class);
	
	@Override
	public MonitorProject findProjectByMonitorIdAndType(Integer monitorId, Integer monitorType) {
		try {
			Criteria c = createCriteria();
			c.add(Restrictions.eq("consumerId.id", monitorId));
			c.add(Restrictions.eq("recordType", monitorType));
			MonitorProject project = (MonitorProject) c.uniqueResult();
			if(project != null){
				return project;
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(TimeUtils.getCurrentTime("")+"--查询已上传实时监控项目信息异常！异常原因:"+e.getMessage());
			return null;
		}
	}

	
}
