package com.jumper.hospital.service;

import com.jumper.hospital.base.Page;
import com.jumper.hospital.entity.Admin;
import com.jumper.hospital.entity.NewsInformation;

public interface NewsInformationService extends BaseService<NewsInformation, Integer> {

	/**
	 * 删除频道时，删除该频道下所有的资讯信息
	 * @param chanelId
	 */
	public Integer deleteByChanelId(Integer chanelId);
	
	/**
	 * 资讯首页查询方法
	 * @param publish 是否发布 0：未发布，1：已发布
	 * @param banner 是否是banner广告 0：否，1：是
	 * @param searchKey 资讯新闻标题搜索关键字
	 * @param chanelId 频道查询
	 * @param periodId 孕期阶段查询
	 * @param belog 素材库来源 0：天使医院，1：本医院
	 * @param page 分页参数
	 * @param hospitalId 医院ID
	 * @return
	 */
	public Page<NewsInformation> search(Integer publish, Integer banner, String searchKey, Integer chanelId, 
			Integer periodId, Integer belog, Page<NewsInformation> page, Integer hospitalId);
	/**
	 * 编辑资讯信息
	 * @param publish
	 * @param info
	 * @return
	 */
	public String editNewsInfo(Integer publish, NewsInformation info, Integer belong, Integer hospitalId);
	/**
	 * 发布资讯
	 * @param id
	 * @param belong
	 * @param hospitalId
	 * @return
	 */
	public String publishNewsInfo(Integer id, Integer belong, Integer hospitalId, Integer chanel);
	/**
	 * 设置banner
	 * @param newsId
	 * @param periods
	 * @return
	 */
	public String addBannerInfo(Integer newsId, String periods);
	/**
	 * 新闻推送
	 * @param id
	 * @return
	 */
	public String newsPush(Integer id, Admin admin);
	
	public void deleteCourseNewsByNewsId(Integer newsId);
}
