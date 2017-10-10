package com.jumper.hospital.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.jumper.hospital.dao.UserHeartrateReportFileDao;
import com.jumper.hospital.entity.UserHeartrateReportFile;

@Repository
public class UserHeartrateReportFileDaoImpl extends BaseDaoImpl<UserHeartrateReportFile,Integer> implements UserHeartrateReportFileDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<UserHeartrateReportFile> findReportByConditon( UserHeartrateReportFile reportFile) {
		try {
			if(reportFile != null){
				Criteria c = createCriteria();
				if(reportFile.getHeartId() != null){
					c.add(Restrictions.eq("heartId", reportFile.getHeartId()));
				}
				if(reportFile.getMonitorUserName() != null){
					c.add(Restrictions.eq("monitorUserName", reportFile.getMonitorUserName()));
				}
				if(null != reportFile.getType()){
					c.add(Restrictions.eq("type", reportFile.getType()));
				}
				if(null != reportFile.getOffsets()){
					c.add(Restrictions.eq("offsets", reportFile.getOffsets()));
				}
				return c.list();
			}
			return null;
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
	}

}
