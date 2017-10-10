package com.jumper.hospital.dao;

import java.util.List;


import com.jumper.hospital.entity.UserHeartrateReportFile;

public interface UserHeartrateReportFileDao extends BaseDao<UserHeartrateReportFile, Integer> {


	 List<UserHeartrateReportFile>  findReportByConditon(UserHeartrateReportFile reportFile);
}
