package com.jumper.hospital.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jumper.hospital.dao.BaseDao;
import com.jumper.hospital.dao.HospitalDoctorInfoDao;
import com.jumper.hospital.dao.HospitalInfoDao;
import com.jumper.hospital.dao.NetWorkManageDao;
import com.jumper.hospital.entity.HospitalDoctorInfo;
import com.jumper.hospital.entity.HospitalInfo;
import com.jumper.hospital.service.HospitalInfoService;
import com.jumper.hospital.utils.ArrayUtils;
import com.jumper.hospital.utils.Consts;
import com.jumper.hospital.utils.HttpRequestUtils;
import com.jumper.hospital.vo.VoHospitalAutoComplete;
import com.jumper.hospital.vo.VoWeightHttpRequest;

import com.alibaba.fastjson.JSON;
/**
 * 医院信息
 * @author tanqing
 *
 */
@Service
public class HospitalInfoServiceImpl extends BaseServiceImpl<HospitalInfo, Integer> implements HospitalInfoService{
	
	private static final Logger logger = Logger.getLogger(HospitalInfoServiceImpl.class);
	
	@Autowired
	private HospitalInfoDao hospitalInfoDaoImpl;
	
	@Autowired
	private NetWorkManageDao netWorkManageDao;
	
	@Autowired
	private HospitalDoctorInfoDao hospitalDoctorInfoDao;
	
	@Override
	public BaseDao<HospitalInfo, Integer> getBaseDAO() {
		return hospitalInfoDaoImpl;
	}

	@Override
	public List<VoHospitalAutoComplete> findAutoCompleteList(String q) {
		try {
			if(StringUtils.isEmpty(q)){
				return null;
			}
			List<HospitalInfo> list = hospitalInfoDaoImpl.findForAutoComplete(q);
			
			List<VoHospitalAutoComplete> dataList = new ArrayList<VoHospitalAutoComplete>();
			if(ArrayUtils.isNotEmpty(list)){
				for(HospitalInfo info : list){
					VoHospitalAutoComplete vo = new VoHospitalAutoComplete();
					vo.setId(info.getId());
					vo.setName(info.getName());
					dataList.add(vo);
				}
			}
			return dataList;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("自动补全查询医生信息异常！异常原因:"+e.getMessage());
			return null;
		}
	}

	@Override
	public String operateWeight(Integer state, HospitalInfo hospitalInfo) {
		try {
			if(state == null){
				return Consts.PARAMS_ERROR;
			}
			hospitalInfo.setIsWeight(state == 1 ? true : false);
			hospitalInfo.setIsBlood(state == 1 ? true : false);
			hospitalInfoDaoImpl.save(hospitalInfo);
			/** 此处要调用明华那边初始化数据接口 **/
			String result = HttpRequestUtils.sendGet(Consts.WEIGHT_REQUEST_URL, "hospitalId="+hospitalInfo.getId());
			logger.info("开通体重管理返回结果："+result);
			VoWeightHttpRequest voResult = JSON.parseObject(result, VoWeightHttpRequest.class);
			if(voResult != null && voResult.getResult() == 1){
				return Consts.SUCCESS;
			}else{
				if(voResult != null){
					logger.info("医院开通体重管理数据初始化失败，失败医院ID:"+hospitalInfo.getId());
				}
				hospitalInfo.setIsWeight(false);
				hospitalInfo.setIsBlood(false);
				hospitalInfoDaoImpl.save(hospitalInfo);
				return Consts.FAILED;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return Consts.ERROR;
		}
	}

	@Override
	public String operateSchool(Integer state, HospitalInfo hospitalInfo) {
		if(state == null){
			return Consts.PARAMS_ERROR;
		}
		hospitalInfo.setIsSchool(state == 1 ? true : false);
		hospitalInfoDaoImpl.save(hospitalInfo);
		return Consts.SUCCESS;
	}

	@Override
	public String operateClass(Integer state, HospitalInfo hospitalInfo) {
		if(state == null){
			return Consts.PARAMS_ERROR;
		}
		hospitalInfo.setIsClass(state == 1 ? true : false);
		hospitalInfoDaoImpl.save(hospitalInfo);
		return Consts.SUCCESS;
	}

	@Override
	@Transactional
	public String networkClass(Integer state, HospitalInfo hospitalInfo) {
		if(state == null){
			return Consts.PARAMS_ERROR;
		}
		hospitalInfo.setIsNetwork(state == 1 ? true : false);
		hospitalInfo=hospitalInfoDaoImpl.persist(hospitalInfo);
		if(hospitalInfo!=null){
			return Consts.SUCCESS;
		}
		return Consts.PARAMS_ERROR;
	}

	@Override
	public String oepratePersonPrivacy(Integer state, HospitalInfo hospitalInfo) {
		if(state == null){
			return Consts.PARAMS_ERROR;
		}
		//hospitalInfo.setIsMobile(state == 1 ? true : false);
		hospitalInfo = hospitalInfoDaoImpl.save(hospitalInfo);
		if(hospitalInfo!=null){
			return Consts.SUCCESS;
		}
		return Consts.PARAMS_ERROR;
	}


	/**
	 * 根据科室关联表ID查询 有无开放的排班信息
	 * @param state 状态:1，开通  0，关闭
	 * @param hospitalInfo 医院信息
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Object> findschedulingByMajorID(Integer majorId,Integer hospid,String datestr) {
		List<Object> list=null;
		try{
			  StringBuffer str=new StringBuffer(""); 
				if(majorId!=null){
					str.append(" and majorid='"+majorId+"'");
				}else if(hospid!=null){
					str.append(" and hospid='"+hospid+"'");
				}
				String sql="select * from network_scheduling WHERE  datas>'"+datestr+"'"+str;
			     list=netWorkManageDao.executeNativeSql(sql);
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return list;
	}

	@Override
	public String operateLease(Integer state, HospitalInfo hospitalInfo) {
		if(state == null){
			return Consts.PARAMS_ERROR;
		}
		hospitalInfo.setIsLease(state == 1 ? true : false);
		hospitalInfoDaoImpl.save(hospitalInfo);
		return Consts.SUCCESS;
	}

	public HospitalDoctorInfo selectDoctorById(Integer id) {
		return  hospitalDoctorInfoDao.selectDoctorById(id);
	}

	@SuppressWarnings("unused")
	@Override
	@Transactional
	public boolean updateNstState(Integer hospitalId, 
			Boolean isDoctorNst) {
		try{
			if(null!=isDoctorNst){
				String sql="update hospital_info set is_doctor_nst="+isDoctorNst+" where id="+hospitalId;
				int i=hospitalInfoDaoImpl.executeNonQuerySql(sql);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public HospitalInfo getHospitalInfo(Integer hospitalId) {
		HospitalInfo hospitalInfo= hospitalInfoDaoImpl.findUniqueBy("id", hospitalId);
		return hospitalInfo;
	}

	/**
	 * 保存预警记录上下限
	 * @return 
	 */
	@Override
	public void updateWarning(Integer hospitalId, int upperLimit, int lowerLimit) {
		String sql= "update monitor_hospital set upper_limit="+upperLimit+",lower_limit="+lowerLimit+" where hospital_id="+hospitalId;
		hospitalInfoDaoImpl.executeNonQuerySql(sql);
	}
	
	/**
	 * 保存是否短信通知和持续异常分钟数
	 */
	@Override
	public void updateBtWarningMessage(Integer hospitalId, boolean isCheck, Integer sel_minute) {
		int checked = 0;
		if(isCheck == true){
			checked = 1;//表示选中
		}else if(isCheck == false){
			checked = 0;//表示未选中
		}
		int minute = sel_minute+1; //选中的分钟数
		String sql= "update monitor_hospital set open_msg="+checked+",abnormal_time="+minute+" where hospital_id =" + hospitalId;
		hospitalInfoDaoImpl.executeNonQuerySql(sql);
	}
}
