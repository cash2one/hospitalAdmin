package com.jumper.hospital.dao;

import java.util.List;

import com.jumper.hospital.base.Page;
import com.jumper.hospital.entity.NewsMessage;

public interface  NewsMessageDao  extends BaseDao<NewsMessage, Integer> {
	public boolean  saveNewsInformation(NewsMessage newsMessage);
	
	

	public Page<NewsMessage> newsList(Page<NewsMessage> page,Integer hospId,String keywords);
	
	public boolean deleteNewsMyMessage(Integer id);

	/**
	 * 通过孕周查询今天有没有推送消息
	 * @param pregnantWeek
	 * @return
	 */
	public List<NewsMessage> findMessagesByPregnantWeek(String pregnantWeek);
}
