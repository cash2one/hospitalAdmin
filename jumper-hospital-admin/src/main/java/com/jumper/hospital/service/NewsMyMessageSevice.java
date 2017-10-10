package com.jumper.hospital.service;

import java.util.List;

import com.jumper.hospital.base.Page;
import com.jumper.hospital.entity.Admin;
import com.jumper.hospital.entity.HospitalInfo;
import com.jumper.hospital.entity.NewsMessage;

public interface NewsMyMessageSevice extends BaseService<NewsMessage, Integer>{
   	
	public String addOrUpdate(NewsMessage news,String  id);

    public  Page<NewsMessage> list(Page<NewsMessage> page,Integer hospId,String keywords);
    
    public   NewsMessage editNewsMessage(Integer id );
    
    public boolean deleteNewsMyMessage(Integer id);

    /**
     * 根据孕周查询今天有没有推送消息
     * @param pregnantWeek
     * @return
     */
	public List<NewsMessage> findMessagesByPregnantWeek(String pregnantWeek);

	/**
	 * 插入医院消息
	 * @param newsMessage
	 * @return
	 */
	public NewsMessage saveMyMessage(String content, Admin admin,
			HospitalInfo hospitalInfo, int pregnantWeek, String pregnant_Week);
	
	/**
	 * 	插入消息表
		 * @param @return
		 * @return NewsMessage    返回类型 
		 * @throws
	 */
	public NewsMessage saveMyMessageforHttp(NewsMessage newsMessage);
}
