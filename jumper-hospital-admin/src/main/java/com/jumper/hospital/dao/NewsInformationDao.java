package com.jumper.hospital.dao;

import java.util.List;

import com.jumper.hospital.base.Page;
import com.jumper.hospital.entity.NewsInformation;

public interface NewsInformationDao extends BaseDao<NewsInformation, Integer> {
	
	/**
	 * 删除频道下的资讯新闻
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
	 * @param belong 素材库来源 0：天使医院，1：本医院
	 * @param page 分页信息
	 * @param hospitalId 所属医院
	 * @return
	 */
	public Page<NewsInformation> search(Integer publish, Integer banner, String searchKey, 
			Integer chanelId, Integer periodId, Integer belong, Page<NewsInformation> page, Integer hospitalId);
	
	public void clearSession();
	
	public List<Object> findIdsByChanelId(Integer chanelId);
	
}
