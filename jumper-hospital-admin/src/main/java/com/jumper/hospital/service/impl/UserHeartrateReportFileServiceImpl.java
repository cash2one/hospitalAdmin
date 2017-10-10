package com.jumper.hospital.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jumper.hospital.dao.BaseDao;
import com.jumper.hospital.dao.UserHeartrateReportFileDao;
import com.jumper.hospital.entity.UserHeartrateReportFile;
import com.jumper.hospital.service.UserHeartrateReportFileService;

@Service
public class UserHeartrateReportFileServiceImpl  extends BaseServiceImpl<UserHeartrateReportFile, Integer> implements UserHeartrateReportFileService {

	@Autowired
	private UserHeartrateReportFileDao userHeartrateReportFileDao;
	
	@Override
	public BaseDao<UserHeartrateReportFile, Integer> getBaseDAO() {
		return userHeartrateReportFileDao;
	}

	@Override
	public List<UserHeartrateReportFile> findReportByCondition(UserHeartrateReportFile reportFile) {
		return userHeartrateReportFileDao.findReportByConditon(reportFile);
	}

}
