package com.jumper.hospital.service;

import java.util.List;

import com.jumper.hospital.entity.HospitalDoctorInfo;
import com.jumper.hospital.entity.HospitalInfo;
import com.jumper.hospital.vo.VoHospitalAutoComplete;
/**
 * 医院信息
 * @author tanqing
 *
 */
public interface HospitalInfoService extends BaseService<HospitalInfo, Integer>{

	/**
	 * 自动补全查询医院
	 * @param q 医院名称
	 * @return List<VoHospitalAutocomplete>
	 */
	public List<VoHospitalAutoComplete> findAutoCompleteList(String q);
	
	/**
	 * 开通关闭体重管理
	 * @param state 状态:1，开通  0，关闭
	 * @param hospitalInfo 医院信息
	 * @return
	 */
	public String operateWeight(Integer state, HospitalInfo hospitalInfo);
	
	/**
	 * 开通关闭孕妇学校
	 * @param state 状态:1，开通  0，关闭
	 * @param hospitalInfo 医院信息
	 * @return
	 */
	public String operateSchool(Integer state, HospitalInfo hospitalInfo);
	
	/**
	 * 开通关闭设备租赁
	 * @param state 状态:1，开通  0，关闭
	 * @param hospitalInfo 医院信息
	 * @return
	 */
	public String operateLease(Integer state, HospitalInfo hospitalInfo);
	/**
	 * 开通关闭课堂管理
	 * @param state 状态:1，开通  0，关闭
	 * @param hospitalInfo 医院信息
	 * @return
	 */
	public String operateClass(Integer state, HospitalInfo hospitalInfo);
	
	
	/**
	 * 开通关闭网络诊室
	 * @param state 状态:1，开通  0，关闭
	 * @param hospitalInfo 医院信息
	 * @return
	 */
	public String networkClass(Integer state, HospitalInfo hospitalInfo);

	
	/**
	 * 根据科室关联表ID查询 有无开放的排班信息
	 * @param state 状态:1，开通  0，关闭
	 * @param hospitalInfo 医院信息
	 * @return
	 */
	public List<Object> findschedulingByMajorID(Integer majorId,Integer hospid,String datestr);
	
	

	

	/**
	 * 屏蔽用户手机号码
	 * @param state
	 * @param info
	 * @return
	 */
	public String oepratePersonPrivacy(Integer state, HospitalInfo hospitalInfo);
	
	
	/**
	 * 
	 *查询医生信息
	 */
	public HospitalDoctorInfo selectDoctorById(Integer id);
	/**
	 * 修改院内胎心和 胎心监护的状态
	 * @param isHospitalNstState
	 * @param isDoctorNst
	 * @return
	 */
	public boolean updateNstState(Integer hospitalId,
			Boolean isDoctorNst);
	
	/**
	 * 获取医院信息
	 * @param hospitalId
	 * @return
	 */
	public HospitalInfo getHospitalInfo(Integer hospitalId);
	
	/**
	 * 保存预警上下限记录
	 * @param hospitalId
	 * @param upperLimit
	 * @param lowerLimit
	 * @return 
	 */
	public void updateWarning(Integer hospitalId, int upperLimit, int lowerLimit);
	
	/**
	 * 保存是否短信通知和持续异常分钟数
	 * @param hospitalId
	 * @param isCheck
	 * @param minute
	 */
	public void updateBtWarningMessage(Integer hospitalId, boolean isCheck, Integer sel_minute);
	
}
