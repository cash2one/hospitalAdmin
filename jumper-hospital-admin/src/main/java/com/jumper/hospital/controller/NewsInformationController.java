package com.jumper.hospital.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jumper.hospital.base.Page;
import com.jumper.hospital.entity.Admin;
import com.jumper.hospital.entity.HospitalInfo;
import com.jumper.hospital.entity.NewsInformationMessage;
import com.jumper.hospital.service.NewsInformationMessageService;
import com.jumper.hospital.utils.Consts;
import com.jumper.hospital.utils.JsonUtils;
import com.jumper.hospital.utils.TimeUtils;

/**
 * 
 * 医院资讯
 * @author rent
 * @date 2016-03-23 医院资讯部分重写
 * 注：所有推送只在资讯有发布的情况下才推送消息，如：保存并发布，从未发布列表中发布资讯
 * 
 */
@Controller
@RequestMapping("information")
public class NewsInformationController extends BaseController {
	
	@Autowired
	private NewsInformationMessageService newsInformationMessageServiceImpl;

	/**
	 * 医院资讯首页
	 * @param model
	 * @return
	 */
	@RequestMapping("index")
	public String newsMessageIndex(ModelMap model, Page<NewsInformationMessage> page, String keywords, Integer operate) {
		if(operate == null || operate == 0) {
			operate = 1;
		}
		Admin admin = this.getLoginAdminInfo();
		HospitalInfo hospitalInfo = admin.getHospitalInfo();
		Page<NewsInformationMessage> pages = newsInformationMessageServiceImpl.getInformationList(hospitalInfo.getId(), keywords, page, operate);
		
		model.put("data", pages);
		model.put("keywords", keywords);
		model.put("operate", operate);
		pageLocation(model, Consts.INFORMATION_MODULE, "news", "hospital");
		return "news/news_index";
	}
	/**
	 * 医院资讯详情
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("detail")
	public String newsDetailInfo(Integer id, ModelMap model){
		NewsInformationMessage message = newsInformationMessageServiceImpl.get(id);
		String time = TimeUtils.convertTime(message.getAddTime(), Consts.FORMAT_TIME_THREE)+" "+TimeUtils.getWeekOfDate(message.getAddTime());
		model.put("time", time);
		model.put("news", message);
		return "news/newsDetail";
	}
	/**
	 * 医院资讯信息编辑
	 * @param message
	 * @param type
	 */
	@RequestMapping("edit")
	public void informationEdit(NewsInformationMessage message, Integer type){
		try {
			Admin admin = getLoginAdminInfo();
			String result = newsInformationMessageServiceImpl.updateInformation(message, type, admin);
			JsonUtils.render(getResponse(), result);
		} catch (Exception e) {
			e.printStackTrace();
			JsonUtils.render(getResponse(), Consts.ERROR);
		}
	}
	/**
	 * 医院资讯信息删除
	 * @param id
	 */
	@RequestMapping("delete")
	public void informationDelete(Integer id) {
		try {
			if(id == null || id == 0){
				JsonUtils.render(getResponse(), Consts.PARAMS_ERROR);
				return;
			}
			NewsInformationMessage message = newsInformationMessageServiceImpl.get(id);
			message.setIsDelete(Consts.TRUE);
			NewsInformationMessage result = newsInformationMessageServiceImpl.save(message);
			if(result == null){
				JsonUtils.render(getResponse(), Consts.FAILED);
				return;
			}
			JsonUtils.render(getResponse(), Consts.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			JsonUtils.render(getResponse(), Consts.ERROR);
		}
	}
	/**
	 * 医院资讯发布
	 * @param id
	 * @param operate
	 */
	@RequestMapping("publish")
	public void informationPublish(Integer id) {
		try {
			Admin admin = getLoginAdminInfo();
			String result = newsInformationMessageServiceImpl.publishInformation(id, admin);
			JsonUtils.render(getResponse(), result);
		} catch (Exception e) {
			e.printStackTrace();
			JsonUtils.render(getResponse(), Consts.ERROR);
		}
	}
	/**
	 * 添加首页广告与取消
	 * 备注：广告是医院最多允许4条，运营后台添加一条
	 */
	@RequestMapping("banner")
	public void handlerBanner(Integer id, Integer operate){
		try {
			Admin admin = getLoginAdminInfo();
			Integer hospitalId = admin.getHospitalInfo().getId();
			String result = newsInformationMessageServiceImpl.handlerBanner(id, hospitalId, operate);
			JsonUtils.render(getResponse(), result);
		} catch (Exception e) {
			e.printStackTrace();
			JsonUtils.render(getResponse(), Consts.ERROR);
		}
	}
	
}
