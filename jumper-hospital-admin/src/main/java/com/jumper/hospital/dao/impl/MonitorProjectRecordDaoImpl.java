package com.jumper.hospital.dao.impl;
/**
 * 远程监控项目记录数据Dao实现类
 * @author rent
 * @date 2015-09-30
 */
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.jumper.hospital.dao.MonitorProjectRecordDao;
import com.jumper.hospital.entity.MonitorProjectRecord;

@Repository
public class MonitorProjectRecordDaoImpl extends BaseDaoImpl<MonitorProjectRecord, Integer> implements MonitorProjectRecordDao {

	@Override
	public boolean deleteProjectByConsumerIdAndRecordType(Integer consumerId, Integer recordType) {
		Criteria c = createCriteria();
		c.add(Restrictions.eq("projectId.id", consumerId));
		c.add(Restrictions.eq("recordId", recordType));
		MonitorProjectRecord record = (MonitorProjectRecord) c.uniqueResult();
		if(record != null){
			delete(record);
			return true;
		}
		return false;
	}


}
