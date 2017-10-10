package com.jumper.hospital.dao.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.jumper.hospital.base.Page;
import com.jumper.hospital.base.PageHibernateDao;
import com.jumper.hospital.dao.NewsMessageDao;
import com.jumper.hospital.entity.NewsMessage;
import com.jumper.hospital.utils.TimeUtils;
@Repository
public class NewsMessageDaoImpl extends BaseDaoImpl<NewsMessage, Integer> implements NewsMessageDao {


	@Override
	public Page<NewsMessage> newsList(Page<NewsMessage> page,Integer hospId,String keywords) {
		if(null != hospId && hospId != 0){
			Criteria c =createCriteria();
			c.add(Restrictions.eq("hospitalInfo.id", hospId));
			c.add(Restrictions.eq("isDelete", 0));
			if(StringUtils.isNotBlank(keywords)){
				c.add(Restrictions.or(Restrictions.ilike("title", keywords,MatchMode.ANYWHERE),Restrictions.ilike("description", keywords,MatchMode.ANYWHERE)));
			}
			PageHibernateDao<NewsMessage, Integer> pageDao = new PageHibernateDao<NewsMessage, Integer>();
			Page<NewsMessage> list = pageDao.findPageByCriteria(page, c);
			
			if(null != list){
				return list;
			}
		}
		return null;
	}



	
	@Override
	public boolean saveNewsInformation(
			NewsMessage newsMessage) {
		try {
			this.save(newsMessage);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}




	@Override
	public boolean deleteNewsMyMessage(Integer id) {
		try {
			NewsMessage news=this.get(id);
			if(news!=null){
				news.setIsDelete(1);
				this.update(news);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}




	@SuppressWarnings("unchecked")
	@Override
	public List<NewsMessage> findMessagesByPregnantWeek(String pregnantWeek) {
		Criteria c = createCriteria();
		c.add(Restrictions.eq("pregnantWeek", pregnantWeek));
		c.add(Restrictions.between("addTime", TimeUtils.getCurrentStartTime(0, new Date()), TimeUtils.getCurrentEndTime(0, new Date())));
		return c.list();
	}

}
