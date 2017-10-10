package com.jumper.hospital.service.impl;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.jumper.hospital.dao.BaseDao;
import com.jumper.hospital.dao.HospitalCloudVisitorDao;
import com.jumper.hospital.entity.CloudVisitorAccount;
import com.jumper.hospital.service.HospitalCloudVisitorService;
import com.jumper.hospital.utils.Const;
import com.jumper.hospital.utils.Consts;
import com.jumper.hospital.utils.DesEncryptUtils;
import com.jumper.hospital.utils.JsonUtils;
import com.jumper.hospital.utils.visit.HttpClient;
import com.jumper.hospital.vo.visit.CloudVisitIndex;
import com.jumper.hospital.vo.visit.EditRoleMsg;
import com.jumper.hospital.vo.visit.Index;
import com.jumper.hospital.vo.visit.Visit;

@Service
public class HospitalCloudVisitorServiceImpl extends BaseServiceImpl<CloudVisitorAccount, Integer> implements HospitalCloudVisitorService{
 
	private static final Logger logger = Logger.getLogger(HospitalCloudVisitorServiceImpl.class);
	
	@Autowired
	HospitalCloudVisitorDao visitorDao;
	
	/**
	 * 随访用户注册并绑定天使医生用户
	 */
	public CloudVisitorAccount bindHosVisitorAccount(Map<String, Object> params, Integer docId, Integer hosId) throws Exception {
		CloudVisitorAccount visitInfo = new CloudVisitorAccount();
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("doctor_id", docId);
			map.put("hospital_id", hosId);
			String jsonStr = JsonUtils.write2String(map);
			jsonStr = URLEncoder.encode(jsonStr, "UTF-8");
			
			String str = DesEncryptUtils.encrypt(jsonStr);
			params.put("ps_token", str);
			HttpClient httpClient = new HttpClient(Const.PS_VISIT_HOST_URL + Consts.PS_JH_BIND_VISIT,params);
			String post = httpClient.post();
			logger.info("bindHosVisitorAccount post content is "+post);
			//post ={"data":{"account":"lx_1081","deptCode":"770","deptName":"泌尿外科","sourseId":"8f1b558668a44c619a162e4ac16795cb"},"res":0};
			//String post = "{\"msg\":0,\"msgbox\":null,\"data\":{\"datas\":{\"deptCode\":\"659\",\"deptName\":\"西药剂\",\"sourseId\":\"ea0e433979624d0989ba42388b62e5bc\",\"account\":\"lx_2030\"}}}";
			TypeReference<Visit> typeRef=new TypeReference<Visit>() {};
			Visit visit = JsonUtils.toObject(post, typeRef);
			if(visit.getMsg().equals("0")){//0:绑定成功，其它都失败
				visitInfo.setMsg(visit.getMsg());
				//visitInfo.setMsgbox(visit.getMsgbox());
				visitInfo.setDeptCode(visit.getData().getDatas().getDeptCode());
				visitInfo.setDeptName(visit.getData().getDatas().getDeptName());
				visitInfo.setSourseId(visit.getData().getDatas().getSourseId());
				visitInfo.setAccount(visit.getData().getDatas().getAccount());
				visitInfo.setJhHospCode(visit.getData().getDatas().getHospCode());
			}else{//绑定失败
				visitInfo.setMsg(visit.getMsg());
				visitInfo.setMsgbox(visit.getMsgbox());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return visitInfo;
	}
	
	/**
	 * 获取随访首页
	 */
	public CloudVisitIndex getCloudIndex(Integer docId, String hospitalId) throws Exception {
		CloudVisitIndex index = new CloudVisitIndex();
		//随访账户
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> params = new HashMap<String, Object>();
		map.put("doctor_id", docId);
		map.put("hospital_id", hospitalId);
		String jsonStr = JsonUtils.write2String(map);
		jsonStr = URLEncoder.encode(jsonStr, "UTF-8");
		
		String str = DesEncryptUtils.encrypt(jsonStr);
		params.put("ps_token", str);
		HttpClient httpClient = new HttpClient(Const.PS_VISIT_HOST_URL + Consts.PS_JH_VISIT_INDEX,params);
		String datas = httpClient.post();
		
		TypeReference<Index> valueTypeRef = new TypeReference<Index>() {};
		
		Index object = JsonUtils.toObject(datas, valueTypeRef);
		
		if(object.getMsg().equals("1")){
		  index = object.getData();
		}
		return index;
	}

	/**
	 * 胎心电话
	 */
	public CloudVisitIndex callToPatient(Integer docId, String hospitalId) throws Exception {
		CloudVisitIndex index = new CloudVisitIndex();
		//随访账户
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> params = new HashMap<String, Object>();
		map.put("doctor_id", docId);
		map.put("hospital_id", hospitalId);
		String jsonStr = JsonUtils.write2String(map);
		jsonStr = URLEncoder.encode(jsonStr, "UTF-8");
		
		String str = DesEncryptUtils.encrypt(jsonStr);
		params.put("ps_token", str);
		HttpClient httpClient = new HttpClient(Const.PS_VISIT_HOST_URL + Consts.PS_JH_VISIT_CALL,params);
		String datas = httpClient.post();
		
		TypeReference<Index> valueTypeRef = new TypeReference<Index>() {};
		
		Index object = JsonUtils.toObject(datas, valueTypeRef);
		
		if(object.getMsg().equals("1")){
		  index = object.getData();
		}
		return index;
	}
	
	/**
	 * 修改云随访角色
	 * @throws Exception 
	 */
	public EditRoleMsg editVisitRole(Map<String, Object> params, Integer docId, Integer hosId) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("doctor_id", docId);
		map.put("hospital_id", hosId);
		String jsonStr = JsonUtils.write2String(map);
		jsonStr = URLEncoder.encode(jsonStr, "UTF-8");
		
		String str = DesEncryptUtils.encrypt(jsonStr);
		params.put("ps_token", str);
		HttpClient httpClient = new HttpClient(Const.PS_VISIT_HOST_URL + Consts.PS_JH_VISIT_EDITROLE,params);
		String post = httpClient.post();
		logger.info("editVisitRole post content is "+post);
		EditRoleMsg editRole = new EditRoleMsg();
		 Map<String, Object> datas = JsonUtils.readObject2Map(post);
		 Integer msg =  (Integer) datas.get("msg");
		 
		 if(msg.equals(0)){
			 editRole.setMsg(msg);
			 return editRole;
		 }else{
			 String msgbox = (String) datas.get("msgbox");
			 editRole.setMsg((Integer) datas.get("msg"));
			 editRole.setMsgbox(msgbox);
			 return editRole;
		 }
	}
	/*@Transactional(readOnly = true)
	public Boolean checkWorkNumHasExist(String workNum){
		return visitorDao.checkWorkNumHasExist(workNum);
	}*/

	@Override
	public BaseDao<CloudVisitorAccount, Integer> getBaseDAO() {
		return visitorDao;
	}

}
