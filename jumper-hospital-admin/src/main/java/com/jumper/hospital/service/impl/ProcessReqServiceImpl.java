/*
 * @文件名： ProcessReqServiceImpl.java
 * @创建人: aaron
 * @创建时间: 2016-2-19
 * @包名： com.jumper.hospital.service.impl
 * @版本： 1.0
 * 版权所有 © 深圳京柏医疗
 * 描述:
 */
package com.jumper.hospital.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.jumper.hospital.base.Page;
import com.jumper.hospital.dao.FamilyDoctorinfoDao;
import com.jumper.hospital.dao.FamilyExaminationArrangedDao;
import com.jumper.hospital.dao.FamilyUserinfoDao;
import com.jumper.hospital.entity.FamilyDoctorinfo;
import com.jumper.hospital.entity.FamilyExaminationArranged;
import com.jumper.hospital.entity.FamilyUserinfo;
import com.jumper.hospital.service.ProcessReqService;
import com.jumper.hospital.utils.Const;
import com.jumper.hospital.utils.MD5EncryptUtils;
import com.jumper.hospital.utils.ReturnMsg;
import com.jumper.hospital.utils.TimeUtils;
import com.jumper.hospital.vo.familyDoctor.VOCity;
import com.jumper.hospital.vo.familyDoctor.VOFamilyDoctorInfo;
import com.jumper.hospital.vo.familyDoctor.VOFamilyExaminationArranged;
import com.jumper.hospital.vo.familyDoctor.VOFamilyUserinfo;
import com.jumper.hospital.vo.familyDoctor.VOProvince;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 类名称：ProcessReqServiceImpl 类描述： 创建人：aaron 创建时间：2016-2-19 下午4:28:32 修改人：aaron
 * 修改时间：2016-2-19 下午4:28:32 修改备注：
 * 
 * @version 1.0
 */
@Service
@Transactional
public class ProcessReqServiceImpl implements ProcessReqService {
	@Autowired
	FamilyDoctorinfoDao familyDoctorinfoDao;
	@Autowired
	FamilyUserinfoDao familyUserinfoDao;
	@Autowired
	FamilyExaminationArrangedDao familyExaminationArrangedDao;
	private <T> T json2Bean(String rootNode, Class<T> clazz) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(rootNode, clazz);
	}

	@SuppressWarnings("static-access")
	@Override
	public ReturnMsg login(String rootNode) throws Exception {
		ReturnMsg ret = new ReturnMsg();
		FamilyDoctorinfo familyDoctorinfo = json2Bean(rootNode,
				FamilyDoctorinfo.class);
		if (StringUtils.isEmpty(familyDoctorinfo.getUsername())) {
			return ret.setReturnMsg(Const.FAILED, "用户名不能为空");
		}
		if (StringUtils.isEmpty(familyDoctorinfo.getPassword())) {
			return ret.setReturnMsg(Const.FAILED, "密码不能为空");
		}
		familyDoctorinfo.setPassword(MD5EncryptUtils
				.getMd5Value(familyDoctorinfo.getPassword()));
		VOFamilyDoctorInfo doctorinfo = familyDoctorinfoDao
				.checkFamilyDoctorInfo(familyDoctorinfo);

		if (null != doctorinfo) {
			Integer provinceId = doctorinfo.getProvinceId();
			Integer cityId = doctorinfo.getCityId();
			VOProvince province = familyDoctorinfoDao
					.getProvinceById(provinceId);
			VOCity city = familyDoctorinfoDao.getCityById(cityId);
			doctorinfo.setCity(city.getCityName());
			doctorinfo.setProvince(province.getProName());
			ArrayList<VOFamilyDoctorInfo> list = new ArrayList<VOFamilyDoctorInfo>();
			list.add(doctorinfo);
			ret.setData(list);
			ret.setMsg(Const.SUCCESS);
			ret.setMsgbox("该用户存在");
		} else {
			ret.setMsg(Const.FAILED);
			ret.setMsgbox("该用户不存在");
		}
		return ret;
	}

	/**
	 * @throws Exception
	 * @see com.jumper.hospital.service.ProcessReqService#getTaskList(com.fasterxml.jackson.databind.JsonNode)
	 */
	@SuppressWarnings("all")
	@Override
	// 根据医生的id获取该医生在当天的任务(包括已完成 )
	public ReturnMsg getTaskList(String rootNode) {
		ReturnMsg ret = new ReturnMsg();
		// 数据处理
		Integer id = null;
		// 状态:0未完成;1已完成
		Short state = null;
		Page<VOFamilyUserinfo> page;

		try {
			ObjectMapper mapper = new ObjectMapper();
			Map map = mapper.readValue(rootNode, Map.class);
			id = Integer.parseInt(map.remove("id") + "");
			String searchName = map.remove("searchName") + "";
			state = (short) Integer.parseInt(map.remove("state") + "");
			page = new Page();
			BeanUtils.copyProperties(page, map);

			// 数据校验 如果参数不全json转对象会出问题
			if (null == id)
				return ret.setReturnMsg(Const.FAILED, "家庭医生id不能为空");
			if (null == state || state > 1 || state < 0)
				return ret.setReturnMsg(Const.FAILED, "家庭医生服务任务状态只能是0 或1");
			// 从排期表中获取当天对应任务的用户
			familyUserinfoDao
					.getUserInfos4Arranged(state, page, id, searchName);

			List<VOFamilyUserinfo> result = page.getResult();
			for (int i = 0; i < result.size(); i++) {
				VOFamilyUserinfo familyUserinfo = result.get(i);
				// 末次月经时间
				Date day = new SimpleDateFormat("yyyy-MM-dd")
						.parse(familyUserinfo.getPregancyDay());
				// 设置孕周
				if (null == day)
					continue;
				Integer pregnantWeek = TimeUtils.getPregnantWeek(day)[0];
				familyUserinfo.setPregnantWeek(pregnantWeek.shortValue());
				// 到排期表中查找已完成的数据
				List<VOFamilyExaminationArranged> task = new ArrayList<VOFamilyExaminationArranged>();
				task = familyExaminationArrangedDao
						.getFamilyExaminationArrangedsByUserInfoId(
								familyUserinfo.getId(), state);
				familyUserinfo.setTask(task);
				// 根据用户id获取当前用户的未完成的下次访问数时间
				FamilyExaminationArranged examinationArranged = familyExaminationArrangedDao
						.getRecentlyFamilyExaminationArrangedByUserInfoId(
								familyUserinfo.getId(), (short) 0);
				// 设置下次访问时间
				if (null != examinationArranged) {
					familyUserinfo.setNextArrangedTime(new SimpleDateFormat(
							"yyyy-MM-dd").format(examinationArranged
							.getHomeVisitsDate()));
				} else {
					familyUserinfo.setNextArrangedTime("");
				}
			}

			ret.setMsg(Const.SUCCESS);
			ret.setMsgbox("查询任务成功");
			ret.setData(page.getResult());
			return ret;
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus()
					.setRollbackOnly();
			ret.setMsg(Const.FAILED);
			ret.setMsgbox("查询任务失败");
			ret.setData(e.getMessage());
			return ret;
		}
	}

	 

	@Override
	public ReturnMsg saveFamilyUserInfo(String data) {
		ReturnMsg ret = new ReturnMsg();
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
			FamilyUserinfo familyUserinfo = this.json2Bean(data,
					FamilyUserinfo.class);
			familyUserinfoDao.persist(familyUserinfo);
			ret.setMsg(Const.SUCCESS);
			ret.setMsgbox("建档成功");
			return ret;
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus()
					.setRollbackOnly();
			ret.setMsg(Const.FAILED);
			ret.setMsgbox("建档失败,手机号码不能重复");
			ret.setData(e.getMessage());
			return ret;
		}
	}

	
	public ReturnMsg createOrUpdateNextArrangedTime(String data)
			throws Exception {
		ReturnMsg ret = new ReturnMsg();
		try {
			// 直接修改familyUserinfo表中的下次服务时间值
			ObjectMapper mapper = new ObjectMapper();
			mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
			// 实例排期表对象
			FamilyExaminationArranged examinationArranged = mapper.readValue(
					data, FamilyExaminationArranged.class);
			// 现根据用户id到排期表中查找未完成的任务的最近的一条数据
			FamilyExaminationArranged recentlyArranged = familyExaminationArrangedDao
					.getRecentlyFamilyExaminationArrangedByUserInfoId(
							examinationArranged.getUserid(), (short) 0);
			// 如果没有数据就创建任务
			if (null == recentlyArranged) {
				examinationArranged.setState((short) 0);
				familyExaminationArrangedDao.save(examinationArranged);
				ret.setMsg(Const.SUCCESS);
				ret.setMsgbox("成功创建下次访问时间");
			} else { // 如果有数据就修改数据
				FamilyExaminationArranged arranged = familyExaminationArrangedDao
						.get(recentlyArranged.getId());
				arranged.setHomeVisitsDate(examinationArranged
						.getHomeVisitsDate());
				familyExaminationArrangedDao.update(arranged);
				ret.setMsg(Const.SUCCESS);
				ret.setMsgbox("成功修改下次访问时间");
			}
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus()
					.setRollbackOnly();
			ret.setMsg(Const.FAILED);
			ret.setMsgbox("创建或修改下次访问时间失败");
			ret.setData(e.getMessage());
		}

		return ret;
	}

	@Override
	public ReturnMsg updateFamilyDoctor(String data) throws Exception {
		ReturnMsg ret = new ReturnMsg();
		try {
			FamilyDoctorinfo doctorinfo = json2Bean(data,
					FamilyDoctorinfo.class);
			FamilyDoctorinfo familyDoctorinfo = familyDoctorinfoDao
					.get(doctorinfo.getId());
			familyDoctorinfo.setPassword(MD5EncryptUtils.getMd5Value(doctorinfo
					.getPassword()));
			familyDoctorinfoDao.update(familyDoctorinfo);
			ret.setMsg(Const.SUCCESS);
			ret.setMsgbox("修改成功");
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus()
					.setRollbackOnly();
			ret.setMsg(Const.FAILED);
			ret.setMsgbox("修改失败");
			ret.setData(e.getMessage());
		}
		return ret;
	}

	@SuppressWarnings("all")
	@Override
	public ReturnMsg getFamilyUserInfos(String data) throws Exception {
		ReturnMsg ret = new ReturnMsg();
		ObjectMapper mapper = new ObjectMapper();
		Map map = mapper.readValue(data, Map.class);
		Integer doctorid = Integer.parseInt(map.remove("doctorid") + "");
		String searchName = (String) map.remove("searchName");
		if (null == doctorid || 0 == doctorid) {
			ret.setMsg(Const.FAILED);
			ret.setMsgbox("没有医生id参数");
		} else {
			Page<VOFamilyUserinfo> page = new Page();
			BeanUtils.copyProperties(page, map);
			if (null == searchName || 0 == searchName.trim().length())
				searchName = "";
			// 根据医生id获取负责的用户数据
			familyUserinfoDao.getFamilyUserInfo4DoctorId(page, doctorid,
					searchName);
			// 遍历获取日程数据
			for (int i = 0; i < page.getResult().size(); i++) {
				VOFamilyUserinfo voFamilyUserinfo = page.getResult().get(i);
				List<VOFamilyExaminationArranged> list = familyExaminationArrangedDao
						.getFamilyExaminationArrangedsByUserInfoId4State(
								voFamilyUserinfo.getId(), (short) 1);
				voFamilyUserinfo.setTask(list);

				// 根据用户id获取当前用户的未完成的下次访问数时间
				FamilyExaminationArranged examinationArranged = familyExaminationArrangedDao
						.getRecentlyFamilyExaminationArrangedByUserInfoId(
								voFamilyUserinfo.getId(), (short) 0);
				// 设置下次访问时间
				if (null != examinationArranged) {
					voFamilyUserinfo.setNextArrangedTime(new SimpleDateFormat(
							"yyyy-MM-dd").format(examinationArranged
							.getHomeVisitsDate()));
				} else {
					voFamilyUserinfo.setNextArrangedTime("");
				}
			}
			ret.setData(page.getResult());
			ret.setMsg(Const.SUCCESS);
			ret.setMsgbox("查询成功");
		}
		return ret;
	}

}