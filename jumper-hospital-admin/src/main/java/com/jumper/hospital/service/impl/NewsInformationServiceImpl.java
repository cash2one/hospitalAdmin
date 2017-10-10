package com.jumper.hospital.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;

import com.jumper.hospital.base.Page;
import com.jumper.hospital.dao.BaseDao;
import com.jumper.hospital.dao.NewsInformationDao;
import com.jumper.hospital.dao.NewsPeriodDao;
import com.jumper.hospital.dao.PregnantCourseIndexDao;
import com.jumper.hospital.dao.PregnantCourseNewsDao;
import com.jumper.hospital.entity.Admin;
import com.jumper.hospital.entity.NewsInformation;
import com.jumper.hospital.entity.NewsPeriod;
import com.jumper.hospital.entity.PregnantCourseIndex;
import com.jumper.hospital.entity.PregnantCourseNews;
import com.jumper.hospital.enums.PregnantStage;
import com.jumper.hospital.service.NewsInformationService;
import com.jumper.hospital.utils.ArrayUtils;
import com.jumper.hospital.utils.BeanUtils;
import com.jumper.hospital.utils.Consts;
import com.jumper.hospital.utils.HttpRequestUtils;
import com.jumper.hospital.utils.TimeUtils;
import com.jumper.hospital.vo.MsgBean;

@Service
public class NewsInformationServiceImpl extends BaseServiceImpl<NewsInformation, Integer> implements NewsInformationService {

	private static final Logger logger = Logger.getLogger(NewsInformationServiceImpl.class);
	
	@Autowired
	private NewsInformationDao newsInformationDaoImpl;
	@Autowired
	private PregnantCourseIndexDao pregnantCourseIndexDaoImpl;
	@Autowired
	private PregnantCourseNewsDao pregnantCourseNewsDaoImpl;
	@Autowired
	private NewsPeriodDao newsPeriodDaoImpl;
	
	@Override
	public BaseDao<NewsInformation, Integer> getBaseDAO() {
		return newsInformationDaoImpl;
	}

	@Override
	public Integer deleteByChanelId(Integer chanelId) {
		List<Object> list = newsInformationDaoImpl.findIdsByChanelId(chanelId);
		StringBuilder ids = new StringBuilder();
		if(ArrayUtils.isNotEmpty(list)){
			for(Object obj : list){
				if(obj != null){
					ids.append(String.valueOf(obj)+",");
				}
			}
		}
		if(StringUtils.isNotEmpty(ids.toString())){
			String idStr = ids.toString().substring(0, ids.toString().length() - 1);
			Integer result = pregnantCourseNewsDaoImpl.deleteByNewsId(idStr);
			if(result < 1){
				logger.info("删除pregnantCourseNews表数据失败！ids:"+idStr);
			}
		}
		return newsInformationDaoImpl.deleteByChanelId(chanelId);
	}

	@Override
	public Page<NewsInformation> search(Integer publish, Integer banner, String searchKey, Integer chanelId, Integer periodId, 
			Integer belog, Page<NewsInformation> page, Integer hospitalId) {
		try {
			Page<NewsInformation> pageData = newsInformationDaoImpl.search(publish, banner, searchKey, chanelId, periodId, belog, page, hospitalId);
			if(publish == 1 && banner != null && banner == 1){
				List<NewsInformation> resultData = new ArrayList<NewsInformation>();
				if(ArrayUtils.isNotEmpty(pageData.getResult())){
					for(NewsInformation news : pageData.getResult()){
						if(news != null){
							List<PregnantCourseNews> pcn = pregnantCourseNewsDaoImpl.getByNewsId(news.getId());
							if(ArrayUtils.isNotEmpty(pcn)){
								List<Integer> pType = new ArrayList<Integer>();
								for(PregnantCourseNews pNews : pcn){
									if(pNews != null){
										PregnantCourseIndex index = pregnantCourseIndexDaoImpl.get(pNews.getPregnantId());
										pType.add(index.getPregnantType());
									}
								}
								String result = ArrayUtils.listToString(pType, "|");
								news.setPeriod(result);
								resultData.add(news);
							}
						}
					}
				}
				pageData.setResult(resultData);
			}
			return pageData;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public String editNewsInfo(Integer publish, NewsInformation info, Integer belong, Integer hospitalId) {
		try {
			if(info == null || belong == null){
				return Consts.PARAMS_ERROR;
			}
			if(info.getId() == null){
				info.setAddTime(TimeUtils.getCurrentTime());
				info.setTop(TimeUtils.getCurrentTime());
				info.setIsPush(0);
				info.setNewsUrl("");
				info.setClicks(0);
				info.setIsFocusImage(0);
				info.setHospitalId(belong);
				info.setShareNum(0);
				info.setPraise(0);
				if(publish != null && publish == 1){
					info.setIsPublish(true);
					info.setPublishTime(TimeUtils.getCurrentTime());
				}else{
					info.setIsPublish(false);
				}
				NewsInformation result = newsInformationDaoImpl.persist(info);
				String[] periods = info.getPeriod().split("\\|");
				for(String p : periods){
					NewsPeriod newsPeriod = new NewsPeriod();
					newsPeriod.setNewsId(info);
					newsPeriod.setPeriod(Integer.valueOf(p));
					newsPeriodDaoImpl.saveNewsPeriod(newsPeriod);
				}
				if(result != null){
					return Consts.SUCCESS;
				}
			}else{
				NewsInformation target = newsInformationDaoImpl.get(info.getId());
				BeanUtils.copy(info, target);
				NewsInformation result = null;
				if(belong == 49){
					NewsInformation news = new NewsInformation();
					BeanUtils.copy(target, news);
					news.setHospitalId(hospitalId);
					
					if(publish != null && publish == 1){
						news.setIsPublish(true);
					}else{
						news.setIsPublish(false);
					}
					news.setPublishTime(TimeUtils.getCurrentTime());
					news.setId(null);
					news.setAddTime(TimeUtils.getCurrentTime());
					news.setIsPush(0);
					news.setNewsUrl("");
					news.setClicks(0);
					news.setIsFocusImage(0);
					news.setShareNum(0);
					news.setPraise(0);
					newsInformationDaoImpl.clearSession();
					result = newsInformationDaoImpl.persist(news);
					
					String[] periods = news.getPeriod().split("\\|");
					for(String p : periods){
						NewsPeriod newsPeriod = new NewsPeriod();
						newsPeriod.setNewsId(news);
						newsPeriod.setPeriod(Integer.valueOf(p));
						newsPeriodDaoImpl.saveNewsPeriod(newsPeriod);
					}
				}else{
					if(publish != null && publish == 1){
						target.setIsPublish(true);
						target.setPublishTime(TimeUtils.getCurrentTime());
					}else{
						target.setIsPublish(false);
					}
					target.setAddTime(TimeUtils.getCurrentTime());
					result = newsInformationDaoImpl.persist(target);
					String[] periods = target.getPeriod().split("\\|");
					for(String p : periods){
						NewsPeriod newsPeriod = new NewsPeriod();
						newsPeriod.setNewsId(target);
						newsPeriod.setPeriod(Integer.valueOf(p));
						newsPeriodDaoImpl.saveNewsPeriod(newsPeriod);
					}
				}
				if(result != null){
					return Consts.SUCCESS;
				}
			}
			return Consts.FAILED;
		} catch (RuntimeException e) {
			e.printStackTrace();
			return Consts.ERROR;
		}
	}

	@Override
	public String publishNewsInfo(Integer id, Integer belong, Integer hospitalId, Integer chanel) {
		try {
			if(id == null || belong == null){
				return Consts.PARAMS_ERROR;
			}
			NewsInformation info = newsInformationDaoImpl.get(id);
			NewsInformation result = null;
			if(belong == 49){
				NewsInformation news = new NewsInformation();
				BeanUtils.copy(info, news);
				news.setId(null);
				news.setHospitalId(hospitalId);
				news.setIsPublish(true);
				news.setPublishTime(TimeUtils.getCurrentTime());
				news.setShareNum(0);
				news.setClicks(0);
				news.setCommentNum(0);
				news.setPraise(0);
				news.setChannelId(chanel);
				newsInformationDaoImpl.clearSession();
				result = newsInformationDaoImpl.save(news);
			}else{
				info.setIsPublish(true);
				info.setPublishTime(TimeUtils.getCurrentTime());
				result = newsInformationDaoImpl.save(info);
			}
			if(result != null){
				return Consts.SUCCESS;
			}
			return Consts.FAILED;
		} catch (RuntimeException e) {
			e.printStackTrace();
			return Consts.ERROR;
		}
	}

	@Override
	public String addBannerInfo(Integer newsId, String periods) {
		try {
			if(newsId == null || StringUtils.isEmpty(periods)){
				return Consts.PARAMS_ERROR;
			}
			NewsInformation news = newsInformationDaoImpl.get(newsId);
			news.setTop(TimeUtils.getCurrentTime());
			newsInformationDaoImpl.save(news);
			boolean flag = true;
			String[] periodArray = periods.split("\\|");
			if(periodArray != null && periodArray.length > 0){
				pregnantCourseNewsDaoImpl.deleteByNewsId(String.valueOf(newsId));
				for(String str : periodArray){
					PregnantStage stage = PregnantStage.getObject(Integer.valueOf(str));
					if(stage == null){
						return Consts.ERROR;
					}
					PregnantCourseIndex index = pregnantCourseIndexDaoImpl.getIndexByWeekAndType(stage.getWeek()[0], stage.getType());
					Set<NewsInformation> newsList = new HashSet<NewsInformation>();
					if(index == null){
						index = new PregnantCourseIndex();
						index.setAddTime(TimeUtils.getCurrentTime());
						index.setPregnantType(stage.getType());
						index.setPregnantWeek(stage.getWeek()[0]);
						newsList.add(news);
					}else{
						newsList.addAll(index.getNews());
						newsList.add(news);
						index.setNews(newsList);
					}
					
					PregnantCourseIndex result = pregnantCourseIndexDaoImpl.save(index);
					if(result == null){
						flag = false;
					}
				}
			}
			if(flag){
				return Consts.SUCCESS;
			}
			return Consts.FAILED;
		} catch (RuntimeException e) {
			e.printStackTrace();
			return Consts.ERROR;
		}
	}

	@Override
	public String newsPush(Integer id, Admin admin) {
		try {
			if(id == null){
				return Consts.PARAMS_ERROR;
			}
			NewsInformation info = newsInformationDaoImpl.get(id);
			boolean flag = true;
			String[] periods = info.getPeriod().split("\\|");
			if(periods != null && periods.length > 0){
				for(String period : periods){
					if(StringUtils.isNotEmpty(period)){
						String pushTag = String.format(Consts.CLASS_PUSH_TAG, new Object[]{admin.getHospitalInfo().getId(), info.getChannelId(), period});
						String params = "id=" + info.getId() + "&content=" + info.getTitle() + "&title=" + admin.getHospitalInfo().getName() 
							+ "&language=cn&Msg_type=2&user_type=3&user_msg=" + pushTag;
						String pushResult = HttpRequestUtils.sendPost(Consts.PUSH_MESSAGE_URL, params);
						MsgBean bean = JSONArray.parseObject(pushResult, MsgBean.class);
			        	if(bean.getMsg() != 1){
			        		flag = false;
			        		break;
			        	}
					}
				}
			}
			logger.info("医院定制课堂推送消息：" + (flag ? "推送成功" : "推送失败"));
        	if(!flag){
        		return Consts.FAILED;
        	}
        	return Consts.SUCCESS;
		} catch (RuntimeException e) {
			e.printStackTrace();
			return Consts.ERROR;
		}
	}

	@Override
	public void deleteCourseNewsByNewsId(Integer newsId) {
		pregnantCourseNewsDaoImpl.deleteByNewsId(newsId+"");
	}

}
