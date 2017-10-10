package com.jumper.hospital.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jumper.hospital.dao.BaseDao;
import com.jumper.hospital.dao.MonitorUserInfoDao;
import com.jumper.hospital.entity.MonitorUserInfo;
import com.jumper.hospital.service.MonitorUserInfoService;
import com.jumper.hospital.utils.ArrayUtils;
import com.jumper.hospital.utils.Consts;
import com.jumper.hospital.utils.TimeUtils;
import com.jumper.hospital.vo.VoMonitorUserAutoComplete;

@Service
public class MonitorUserInfoServiceImpl extends BaseServiceImpl<MonitorUserInfo, Integer> implements MonitorUserInfoService {

	private static final Logger logger = Logger.getLogger(MonitorUserInfoServiceImpl.class);
	@Autowired
	private MonitorUserInfoDao monitorUserInfoDaoImpl;
	
	@Override
	public BaseDao<MonitorUserInfo, Integer> getBaseDAO() {
		return monitorUserInfoDaoImpl;
	}
	
	public List<MonitorUserInfo> convertRedisQueueToUserList(List<String> queueList) {
		List<MonitorUserInfo> userList = new ArrayList<MonitorUserInfo>();
		try {
			if(ArrayUtils.isNotEmpty(queueList)){
				for(String str : queueList){
					if(StringUtils.isNumeric(str)){
						MonitorUserInfo info = monitorUserInfoDaoImpl.getMonitorUserInfo(Integer.parseInt(str));
						if(info != null){
							userList.add(info);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(TimeUtils.getCurrentTime("")+"--Redis队列用户信息转换异常!异常原因:"+e.getMessage());
		}
		return userList;
	}

	@Override
	public List<VoMonitorUserAutoComplete> findUserAutoComplete(String mobile) {
		try {
			List<MonitorUserInfo> userList = monitorUserInfoDaoImpl.findUserAutoComplete(mobile);
			List<VoMonitorUserAutoComplete> voList = new ArrayList<VoMonitorUserAutoComplete>();
			if(ArrayUtils.isEmpty(userList)){
				return null;
			}
			for(MonitorUserInfo info : userList){
				VoMonitorUserAutoComplete vo = new VoMonitorUserAutoComplete();
				vo.setId(info.getId());
				if(info.getUserId()!=null &&info.getUserId().getUserExitInfo()!=null){
					vo.setAge(info.getUserId().getUserExitInfo().getAge()==null?info.getAge():info.getUserId().getUserExitInfo().getAge());
					vo.setName(info.getUserId().getUserExitInfo().getRealName()==null?info.getRealName():info.getUserId().getUserExitInfo().getRealName());
				}else{
					vo.setAge(info.getAge());
					vo.setName(info.getRealName());
				}

				vo.setMobile(info.getMobile());
				vo.setAddress(info.getAddress());
				if( info.getUserId() != null && info.getUserId().getExpectedDateOfConfinement() != null){
					vo.setEdc(TimeUtils.convertTime(info.getUserId().getExpectedDateOfConfinement(), Consts.FORMAT_TIME_THREE));
				}else{
					if(info.getEdc() != null){
						vo.setEdc(TimeUtils.convertTime(info.getEdc(), Consts.FORMAT_TIME_THREE));
					}else{
						vo.setEdc("");
					}
				}
				voList.add(vo);
			}
			return voList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public MonitorUserInfo doCheckMobileIsExist(String mobile) {
		return monitorUserInfoDaoImpl.findMUSerByMobile(mobile);
	}


}
