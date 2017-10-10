package com.jumper.hospital.service.impl;

import java.sql.Timestamp;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jumper.hospital.base.Page;
import com.jumper.hospital.dao.BaseDao;
import com.jumper.hospital.dao.UserSugarRecordDao;
import com.jumper.hospital.entity.UserSugarRecord;
import com.jumper.hospital.service.UserSugarRecordService;
import com.jumper.hospital.utils.mointorData.TimeUtils;
import com.jumper.hospital.vo.monitorData.Params;

@Service
public class UserSugarRecordServiceImpl extends BaseServiceImpl<UserSugarRecord, Integer> implements UserSugarRecordService{

	@Autowired
	private UserSugarRecordDao userSugarRecordDao ;
	
	@Override
	public BaseDao<UserSugarRecord, Integer> getBaseDAO() {
		return userSugarRecordDao;
	}

	@Override
	public UserSugarRecord findByUserId(Integer userId) {
		return userSugarRecordDao.findByUserId(userId);
	}

	@Override
	public int getCount(Integer userId) {
		return userSugarRecordDao.getCount(userId);
	}

	@Override
	public Page<UserSugarRecord> findByConditionWithPage(UserSugarRecord sugar, Page<UserSugarRecord> page,Params params) {
		String timeFlag = params.getTimeFlag();
		String startTime = params.getStartTime();
		String endTime = params.getEndTime();
		Timestamp staTime = null;
		Timestamp edTime = null;
		if(StringUtils.isNotBlank(timeFlag) && StringUtils.isNumeric(timeFlag)){//说明不是自定义时间区域查询
			staTime	=	TimeUtils.getBefourOrAfterDay(-Integer.valueOf(timeFlag)+1,"yyyy-MM-dd 00:00:00");
			edTime	=	TimeUtils.getBefourOrAfterDay(1,"yyyy-MM-dd 23:59:59");
		}else if(StringUtils.isNotBlank(startTime)){//是自定义时间区域查询
			if(StringUtils.isBlank(endTime)){
				//如果结束时间为空 那么结束时间默认为当前时间
				endTime = TimeUtils.converStringDate(new Date(), "yyyy-MM-dd HH:mm:ss");
			}
			staTime =TimeUtils.getCurrentTime(startTime+" 00:00:00", "yyyy-MM-dd HH:mm:ss");
			edTime =TimeUtils.getCurrentTime(endTime+" 23:59:59", "yyyy-MM-dd HH:mm:ss");
		}
		return userSugarRecordDao.findByConditionWithPage(sugar,page,staTime,edTime);
	}
	

}
