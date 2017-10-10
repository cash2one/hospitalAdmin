package com.jumper.hospital.service.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jumper.hospital.base.Page;
import com.jumper.hospital.dao.BaseDao;
import com.jumper.hospital.dao.UserWeightRecordDao;
import com.jumper.hospital.entity.UserWeightRecord;
import com.jumper.hospital.service.UserWeightRecordService;
import com.jumper.hospital.utils.mointorData.TimeUtils;
import com.jumper.hospital.vo.monitorData.Params;

@Service
public class UserWeightRecordServiceImpl extends BaseServiceImpl<UserWeightRecord, Integer> implements  UserWeightRecordService {

	@Autowired
	private UserWeightRecordDao userWeightRecordDao;
		
	@Override
	public BaseDao<UserWeightRecord, Integer> getBaseDAO() {
		return userWeightRecordDao;
	}

	@Override
	public int getCount(Integer userId) {
		return userWeightRecordDao.getCount(userId);
	}

	@Override
	public List<UserWeightRecord> findByCondtion( UserWeightRecord userWeightRecord,Params params) {
		String timeFlag = params.getTimeFlag();
		String startTime = params.getStartTime();
		String endTime = params.getEndTime();
		Timestamp ST = null;
		Timestamp ET = null;
		if(StringUtils.isNotBlank(timeFlag) && StringUtils.isNumeric(timeFlag)){//说明不是自定义时间区域查询
			ST	=	TimeUtils.getBefourOrAfterDay(-Integer.valueOf(timeFlag)+1,"yyyy-MM-dd 00:00:00");
			ET	=	TimeUtils.getBefourOrAfterDay(1,"yyyy-MM-dd 23:59:59");
		}else if(StringUtils.isNotBlank(startTime)){//是自定义时间区域查询
			if(StringUtils.isBlank(endTime)){
				//如果结束时间为空 那么结束时间默认为当前时间
				endTime = TimeUtils.converStringDate(new Date(), "yyyy-MM-dd HH:mm:ss");
			}
			ST =TimeUtils.getCurrentTime(startTime+" 00:00:00", "yyyy-MM-dd HH:mm:ss");
			ET =TimeUtils.getCurrentTime(endTime+" 23:59:59", "yyyy-MM-dd HH:mm:ss");
		}
		return userWeightRecordDao.findByCondition(userWeightRecord,ST,ET);
	}

	@Override
	public Page<UserWeightRecord> getUserWeightRecordWithPage( Page<UserWeightRecord> page,UserWeightRecord weightRecord,Params params) {
		String timeFlag = params.getTimeFlag();
		String startTime = params.getStartTime();
		String endTime = params.getEndTime();
		Timestamp ST = null;
		Timestamp ET = null;
		if(StringUtils.isNotBlank(timeFlag) && StringUtils.isNumeric(timeFlag)){//说明不是自定义时间区域查询
			ST	=	TimeUtils.getBefourOrAfterDay(-Integer.valueOf(timeFlag)+1,"yyyy-MM-dd 00:00:00");
			ET	=	TimeUtils.getBefourOrAfterDay(1,"yyyy-MM-dd 23:59:59");
		}else if(StringUtils.isNotBlank(startTime)){//是自定义时间区域查询
			if(StringUtils.isBlank(endTime)){
				//如果结束时间为空 那么结束时间默认为当前时间
				endTime = TimeUtils.converStringDate(new Date(), "yyyy-MM-dd HH:mm:ss");
			}
			ST =TimeUtils.getCurrentTime(startTime+" 00:00:00", "yyyy-MM-dd HH:mm:ss");
			ET =TimeUtils.getCurrentTime(endTime+" 23:59:59", "yyyy-MM-dd HH:mm:ss");
		}
		return userWeightRecordDao.getUserWeightRecordWithPage(page,weightRecord,ST,ET);
	}



	
}
