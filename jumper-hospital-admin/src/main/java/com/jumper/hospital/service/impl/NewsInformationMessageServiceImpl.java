package com.jumper.hospital.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;

import com.jumper.hospital.base.Page;
import com.jumper.hospital.dao.BaseDao;
import com.jumper.hospital.dao.NewsInformationMessageDao;
import com.jumper.hospital.entity.Admin;
import com.jumper.hospital.entity.NewsInformationMessage;
import com.jumper.hospital.service.NewsInformationMessageService;
import com.jumper.hospital.utils.BeanUtils;
import com.jumper.hospital.utils.Consts;
import com.jumper.hospital.utils.HttpRequestUtils;
import com.jumper.hospital.utils.TimeUtils;
import com.jumper.hospital.vo.MsgBean;

@Service
public class NewsInformationMessageServiceImpl extends BaseServiceImpl<NewsInformationMessage, Integer> implements NewsInformationMessageService {
	
	private static final Logger logger = Logger.getLogger(NewsInformationMessageServiceImpl.class);
	@Autowired
	private NewsInformationMessageDao newsInformationMessageDaoImpl;
	
	@Override
	public BaseDao<NewsInformationMessage, Integer> getBaseDAO() {
		return newsInformationMessageDaoImpl;
	}
	
	@Override
	public Page<NewsInformationMessage> getInformationList(Integer hospId, String keywords, Page<NewsInformationMessage> page, Integer operate) {
		if(hospId == null || hospId == 0){
			return null;
		}
		Page<NewsInformationMessage> pageList = newsInformationMessageDaoImpl.getInformationList(hospId, keywords, page, operate);
		return pageList;
	}
	
	@Override
	public String updateInformation(NewsInformationMessage message, Integer type, Admin admin) {
		try {
			if(message == null || type == null){
				return Consts.PARAMS_ERROR;
			}
			if(message.getId() != null){
				/** 修改资讯信息 **/
				NewsInformationMessage newsInfo = newsInformationMessageDaoImpl.get(message.getId());
				if(newsInfo == null){
					return Consts.FAILED;
				}
				BeanUtils.copy(message, newsInfo);
				newsInfo.setModifyTime(TimeUtils.getCurrentTime());
				newsInfo.setModifyEmp(admin.getName());
				NewsInformationMessage result = newsInformationMessageDaoImpl.save(newsInfo);
				if(result == null){
					return Consts.FAILED;
				}
			}else{
				/** 新增 **/
				message.setAddEmp(admin.getName());
				message.setAddTime(TimeUtils.getCurrentTime());
				message.setAdminId(admin);
				message.setHospitalInfo(admin.getHospitalInfo());
				message.setIsBanner(Consts.FALSE);
				message.setIsDelete(Consts.FALSE);
				message.setModifyEmp(admin.getName());
				message.setModifyTime(TimeUtils.getCurrentTime());
				/** type值解释，1：保存并发布，2：保存至未发布 **/
				Integer pushStatus = type == 1 ? Consts.TRUE : Consts.FALSE;
				message.setPushStatus(pushStatus);
				message.setPushTime(TimeUtils.getCurrentTime());
				NewsInformationMessage result = newsInformationMessageDaoImpl.persist(message);
				if(result == null){
					return Consts.FAILED;
				}
				if(type == 1){
					/**
					String params = "id=" + admin.getHospitalInfo().getId() + "&content=" + result.getTitle() + "&title=" + admin.getHospitalInfo().getName() 
						+ "&language=cn&Msg_type=13&user_type=2&user_msg=" + result.getId();
					**/
					
					//外网推送
					String params = "id=" + result.getId() + "&content=" + message.getTitle() + "&title=" + admin.getHospitalInfo().getName() 
						+ "&language=cn&Msg_type=13&user_type=2&user_msg=" + admin.getHospitalInfo().getId();
					
					String pushResult = HttpRequestUtils.sendPost(Consts.PUSH_MESSAGE_URL, params);
					System.out.println("资讯推送结果："+pushResult);
					logger.info("资讯推送结果："+pushResult);
					MsgBean bean = JSONArray.parseObject(pushResult, MsgBean.class);
		        	if(bean.getMsg() != 1){
		        		logger.info("医院资讯推送失败！");
		        		/** 推送失败，状态置回为未发布 **/
		        		result.setPushStatus(Consts.FALSE);
		        		newsInformationMessageDaoImpl.save(result);
		        		return Consts.PUSH_ERROR;
		        	}
				}
			}
			return Consts.SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return Consts.ERROR;
		}
	}

	@Override
	public String publishInformation(Integer id, Admin admin) {
		try {
			if(id == null || id == 0){
				return Consts.PARAMS_ERROR;
			}
			NewsInformationMessage message = newsInformationMessageDaoImpl.get(id);
			message.setPushStatus(Consts.TRUE);
			NewsInformationMessage result = newsInformationMessageDaoImpl.save(message);
			if(result == null){
				return Consts.FAILED;
			}
			//线上
			String params = "id=" + admin.getHospitalInfo().getId() + "&content=" + message.getTitle() + "&title=" + admin.getHospitalInfo().getName() 
				+ "&language=cn&Msg_type=13&user_type=2&user_msg=" + message.getId();
			/**
			String params = "id=" + message.getId() + "&content=" + message.getTitle() + "&title=" + admin.getHospitalInfo().getName() 
				+ "&language=cn&Msg_type=13&user_type=2&user_msg=" + admin.getHospitalInfo().getId();
			**/
			String pushResult = HttpRequestUtils.sendPost(Consts.PUSH_MESSAGE_URL, params);
			System.out.println("资讯推送结果："+pushResult);
			logger.info("资讯推送结果："+pushResult);
			MsgBean bean = JSONArray.parseObject(pushResult, MsgBean.class);
	    	if(bean.getMsg() != 1){
	    		logger.info("医院资讯推送失败！");
	    		return Consts.PUSH_ERROR;
	    	}
			return Consts.SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return Consts.ERROR;
		}
	}

	@Override
	public String handlerBanner(Integer id, Integer hospitalId, Integer operate) {
		try {
			if(id == null || id == 0){
				return Consts.PARAMS_ERROR;
			}
			NewsInformationMessage message = newsInformationMessageDaoImpl.get(id);
			if(operate == 1){
				/** 表示是添加banner **/
				boolean flag = newsInformationMessageDaoImpl.checkBannerCount(id, hospitalId);
				if(flag){
					return Consts.OUT_COUNT;
				}
				message.setPushTime(TimeUtils.getCurrentTime());
			}
			message.setIsBanner(operate == 1 ? Consts.TRUE : Consts.FALSE);
			NewsInformationMessage result = newsInformationMessageDaoImpl.save(message);
			if(result != null){
				return Consts.SUCCESS;
			}
			return Consts.FAILED;
		} catch (Exception e) {
			e.printStackTrace();
			return Consts.ERROR;
		}
	}
	
}

