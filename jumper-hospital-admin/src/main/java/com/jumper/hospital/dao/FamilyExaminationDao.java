package com.jumper.hospital.dao;

import java.util.List;
import java.util.Map;

import com.jumper.hospital.base.Page;
import com.jumper.hospital.entity.FamilyExamination;
import com.jumper.hospital.vo.familyDoctor.VOFamilyExaminationResult;

public interface FamilyExaminationDao extends BaseDao<FamilyExamination, Integer> {

	void getHospitalNotExamineResultByHospitalId(Page<VOFamilyExaminationResult> page,
			Integer hospitalId) throws Exception;
/**
 * 根据用户id获取对应的报告列表
 * @param userid
 * @return
 */
	List<Map<String, Object>> getListByUserid(Integer userid);


}
