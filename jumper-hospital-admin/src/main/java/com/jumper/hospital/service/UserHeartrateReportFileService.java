package com.jumper.hospital.service;

import java.util.List;

import com.jumper.hospital.entity.UserHeartrateReportFile;

public interface UserHeartrateReportFileService extends BaseService<UserHeartrateReportFile,Integer>{

	
	List<UserHeartrateReportFile>  findReportByCondition(UserHeartrateReportFile reportFile);
}
