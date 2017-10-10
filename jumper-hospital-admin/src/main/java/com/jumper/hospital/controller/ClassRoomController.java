package com.jumper.hospital.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jumper.hospital.base.Page;
import com.jumper.hospital.entity.Admin;
import com.jumper.hospital.entity.NewsChanels;
import com.jumper.hospital.entity.NewsInformation;
import com.jumper.hospital.service.NewsChanelsService;
import com.jumper.hospital.service.NewsInformationService;
import com.jumper.hospital.utils.Consts;
import com.jumper.hospital.utils.JsonUtils;
import com.jumper.hospital.utils.TimeUtils;
import com.jumper.hospital.vo.VOBaseResult;
import com.jumper.hospital.vo.VONewsChanelAdd;

@Controller
@RequestMapping("classroom")
public class ClassRoomController extends BaseController {
	
	private static final Logger logger = Logger.getLogger(ClassRoomController.class);

	@Autowired
	private NewsChanelsService newsChanelsServiceImpl;
	@Autowired
	private NewsInformationService newsInformationServiceImpl;
	
	/**
	 * 课堂频道管理首页
	 * @param model
	 * @param page
	 * @return
	 */
	@RequestMapping("chanel")
	public String chanelsIndex(ModelMap model, Page<NewsChanels> page){
		logger.info("进入课堂获取频道列表");
		Admin admin = getLoginAdminInfo();
		int hospitalId = admin.getHospitalInfo().getId();
		Page<NewsChanels> pageData = newsChanelsServiceImpl.findChanelsByPageHospital(page, hospitalId);
		model.put("page", pageData);
		pageLocation(model, Consts.INFORMATION_MODULE, "classroom", "chanel");
		return "classroom/chanel_index";
	}
	/**
	 * 编辑频道信息
	 * @param id
	 * @param chanelName
	 */
	@RequestMapping("eidtChanel")
	public void chanelEdit(Integer id, String chanelName){
		Admin admin = getLoginAdminInfo();
		int hospitalId = admin.getHospitalInfo().getId();
		VONewsChanelAdd result = newsChanelsServiceImpl.editChanelInfo(id, chanelName, hospitalId);
		JsonUtils.render(getResponse(), result);
	}
	/**
	 * 删除频道信息
	 * @param id
	 */
	@RequestMapping("delChanel")
	public void chanelDelete(Integer id){
		try {
			if(id == null || id == 0){
				JsonUtils.render(getResponse(), Consts.FAILED);
				return;
			}
			newsInformationServiceImpl.deleteByChanelId(id);
			newsChanelsServiceImpl.delete(id);
			JsonUtils.render(getResponse(), Consts.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			JsonUtils.render(getResponse(), Consts.ERROR);
		}
	}
	/**
	 * 频道置顶
	 * @param id
	 * @return
	 */
	@RequestMapping("topChanel")
	public String chanelTop(Integer id){
		if(id != null && id != 0){
			Admin admin = getLoginAdminInfo();
			Integer hospitalId = admin.getHospitalInfo().getId();
			NewsChanels chanel = newsChanelsServiceImpl.get(id);
			Integer orderBy = newsChanelsServiceImpl.findMaxOrderByByHospital(hospitalId, "desc");
			chanel.setOrderBy(orderBy+1);
			newsChanelsServiceImpl.save(chanel);
		}
		return "redirect:/classroom/chanel";
	}
	/**
	 * 修改频道的默认订阅及显示属性
	 * @param id
	 * @param type
	 */
	@RequestMapping("state")
	public void changeChanelState(Integer id, Integer operate, Integer type){
		Admin admin = getLoginAdminInfo();
		Integer hospitalId = admin.getHospitalInfo().getId();
		VOBaseResult result = newsChanelsServiceImpl.changeChanelState(id, operate, type, hospitalId);
		JsonUtils.render(getResponse(), result);
	}
	@RequestMapping("news")
	public String newsIndex(ModelMap model, Integer publish, Integer banner, Integer belong, String searchKey, Integer chanelId, 
			Integer periodId, Page<NewsInformation> page){
		try {
			Admin admin = getLoginAdminInfo();
			Integer hospitalId = admin.getHospitalInfo().getId();
			long count = newsChanelsServiceImpl.findChanelNumByHospital(hospitalId);
			if(count < 1){
				model.put("hasChanel", false);
			}else{
				model.put("hasChanel", true);
				publish = publish == null ? 1 : publish;
				banner = banner == null ? 0 : banner;
				belong = belong == null ? hospitalId : belong;
				Page<NewsInformation> pageData = newsInformationServiceImpl.search(publish, banner, searchKey, chanelId, periodId, belong, page, hospitalId);
				List<NewsChanels> chanelList = newsChanelsServiceImpl.findChanelByHospitalId(belong);
				List<NewsChanels> currentHospitalChanels = newsChanelsServiceImpl.findChanelByHospitalId(hospitalId);
				model.put("page", pageData);
				model.put("publish", publish);
				model.put("banner", banner);
				model.put("belong", belong);
				model.put("searchKey", searchKey);
				model.put("chanels", chanelList);
				model.put("chc", currentHospitalChanels);
				model.put("period", periodId);
				model.put("chanelId", chanelId);
			}
			pageLocation(model, Consts.INFORMATION_MODULE, "classroom", "news");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "classroom/news_index";
	}
	/**
	 * 跳转编辑页
	 * @param id
	 * @return
	 */
	@RequestMapping("news_edit")
	public String editPageJump(ModelMap model, Integer id, Integer belong){
		Admin admin = getLoginAdminInfo();
		Integer hospitalId = admin.getHospitalInfo().getId();
		if(id != null && id != 0){
			NewsInformation newsInfo = newsInformationServiceImpl.get(id);
			model.put("news", newsInfo);
		}
		List<NewsChanels> chanelList = newsChanelsServiceImpl.findChanelByHospitalId(hospitalId);
		model.put("chanels", chanelList);
		model.put("belong", belong);
		pageLocation(model, Consts.INFORMATION_MODULE, "classroom", "news");
		return "classroom/news_edit";
	}
	/**
	 * 保存编辑信息
	 * @param publish
	 * @param newsInfo
	 * @param belong
	 */
	@RequestMapping("editInfo")
	public void editNewsInfo(Integer publish, NewsInformation newsInfo, Integer belong){
		try {
			Admin admin = getLoginAdminInfo();
			Integer hospitalId = admin.getHospitalInfo().getId();
			belong = belong == null ? hospitalId : belong;
			String result = newsInformationServiceImpl.editNewsInfo(publish, newsInfo, belong, hospitalId);
			JsonUtils.render(getResponse(), result);
		} catch (Exception e) {
			e.printStackTrace();
			JsonUtils.render(getResponse(), Consts.ERROR);
		}
	}
	/**
	 * 发布资讯
	 * @param id
	 * @param belong
	 */
	@RequestMapping("publish")
	public void publishNewsInfo(Integer id, Integer belong, Integer chanel){
		try {
			Admin admin = getLoginAdminInfo();
			Integer hospitalId = admin.getHospitalInfo().getId();
			String result = newsInformationServiceImpl.publishNewsInfo(id, belong, hospitalId, chanel);
			JsonUtils.render(getResponse(), result);
		} catch (Exception e) {
			e.printStackTrace();
			JsonUtils.render(getResponse(), Consts.ERROR);
		}
	}
	/**
	 * 取消发布
	 * @param id
	 */
	@RequestMapping("cancelPublish")
	public void cancelPublishNewsInfo(Integer id){
		try {
			NewsInformation news = newsInformationServiceImpl.get(id);
			news.setIsPublish(false);
			NewsInformation result = newsInformationServiceImpl.save(news);
			if(result != null){
				JsonUtils.render(getResponse(), Consts.SUCCESS);
				return;
			}
			JsonUtils.render(getResponse(), Consts.FAILED);
		} catch (Exception e) {
			e.printStackTrace();
			JsonUtils.render(getResponse(), Consts.ERROR);
		}
	}
	/**
	 * 资讯详情
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping("detail")
	public String newsDetail(ModelMap model, Integer id){
		NewsInformation newsInfo = newsInformationServiceImpl.get(id);
		model.put("news", newsInfo);
		pageLocation(model, Consts.INFORMATION_MODULE, "classroom", "news");
		return "classroom/news_detail";
	}
	/**
	 * 设置资讯为banner
	 * @param newsId
	 * @param period
	 */
	@RequestMapping("banner")
	public void addBanner(Integer newsId, String periods){
		try {
			String result = newsInformationServiceImpl.addBannerInfo(newsId, periods);
			JsonUtils.render(getResponse(), result);
		} catch (Exception e) {
			e.printStackTrace();
			JsonUtils.render(getResponse(), Consts.ERROR);
		}
	}
	/**
	 * 资讯新闻banner排序
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping("top")
	public String changeNewsOrder(ModelMap model, Integer id){
		NewsInformation newsInfo = newsInformationServiceImpl.get(id);
		newsInfo.setTop(TimeUtils.getCurrentTime());
		newsInformationServiceImpl.save(newsInfo);
		model.put("publish", 1);
		model.put("banner", 1);
		return "redirect:/classroom/news";
	}
	/**
	 * 新闻删除
	 * @param id
	 */
	@RequestMapping("delete")
	public void newsInfoDelete(Integer id){
		try {
			if(id == null){
				JsonUtils.render(getResponse(), Consts.PARAMS_ERROR);
				return;
			}
			newsInformationServiceImpl.deleteCourseNewsByNewsId(id);
			newsInformationServiceImpl.delete(id);
			JsonUtils.render(getResponse(), Consts.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			JsonUtils.render(getResponse(), Consts.ERROR);
		}
	}
	/**
	 * 课堂资讯消息推送
	 * @param id
	 */
	@RequestMapping("push")
	public void newsPush(Integer id){
		try {
			String result = newsInformationServiceImpl.newsPush(id, getLoginAdminInfo());
			JsonUtils.render(getResponse(), result);
		} catch (Exception e) {
			e.printStackTrace();
			JsonUtils.render(getResponse(), Consts.ERROR);
		}
	}
	
}
