package com.jumper.hospital.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.jumper.hospital.base.Page;
import com.jumper.hospital.entity.Admin;
import com.jumper.hospital.entity.FamilyExamination;
import com.jumper.hospital.entity.FamilyUserHeartrateRecord;
import com.jumper.hospital.entity.FamilyUserOxygenRecord;
import com.jumper.hospital.entity.FamilyUserPressureRecord;
import com.jumper.hospital.entity.FamilyUserSugarRecord;
import com.jumper.hospital.entity.FamilyUserTemperatureRecord;
import com.jumper.hospital.entity.FamilyUserWeightRecord;
import com.jumper.hospital.vo.familyDoctor.MonitoringResults;
import com.jumper.hospital.vo.familyDoctor.VOFamilyExaminationResult;

public interface FamilyExaminationService extends BaseService<FamilyExamination, Integer> {
	
/**
 * 根据医院i获取未完成报告的用户信息
 * @param page
 * @param admin
 * @throws Exception 
 */
	void getHospitalNotExamineResultByHospitalId(Page<VOFamilyExaminationResult> page,
			Admin admin) throws Exception;
/**
 * 后台生成报告单
 * @param request
 * @param id
 * @param reason
 * @param name
 * @param offset
 * @return
 */
Boolean generateReport(HttpServletRequest request, Integer id, String reason,String name, Integer offset);
/**
 * 后台生成综合报告单
 * @param request
 * @param id
 * @return
 */
Boolean familyComprehensiveReportForm(HttpServletRequest request, Integer id,String name,String reason);
//保存报告单并更新 状态
Boolean saveReport(HttpServletRequest request, Integer id, String reason,String name, Integer offset);
/**
 * 根据用户的id获取对应的报告列表
 * @param id
 * @return
 */
List<Map<String, Object>> getListByUserid(Integer id);
FamilyUserHeartrateRecord  getHeartRecord(Integer userid, Map<String,Integer> map, List<MonitoringResults> list);
FamilyUserWeightRecord getWeightRecord(Integer userid,  Map<String,Integer> map, List<MonitoringResults> list);
FamilyUserOxygenRecord getOxygenRecord(Integer userid, Map<String,Integer> map, List<MonitoringResults> list);
FamilyUserPressureRecord getPressureRecord(Integer userid,  Map<String,Integer> map, List<MonitoringResults> list);
FamilyUserSugarRecord getSugarRecord(Integer userid, Map<String,Integer> map, List<MonitoringResults> list);
FamilyUserTemperatureRecord getTemperatureRecord(Integer userid,  Map<String,Integer> map, List<MonitoringResults> list);
//获取报告当对应的结果集
Map<String, Integer> getExamineRecords(Integer id, Timestamp addTime);
}
