package com.jumper.hospital.dao.impl;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.jumper.hospital.base.Page;
import com.jumper.hospital.dao.NewsInformationMessageDao;
import com.jumper.hospital.entity.NewsInformationMessage;
import com.jumper.hospital.utils.Consts;

@Repository
public class NewsInfomationMessageDaoImpl extends BaseDaoImpl<NewsInformationMessage, Integer> implements NewsInformationMessageDao {

	@Override
	public Page<NewsInformationMessage> getInformationList(Integer hospId, String keywords, Page<NewsInformationMessage> page, Integer operate) {
		
		Criteria c = createCriteria();
		c.add(Restrictions.eq("hospitalInfo.id", hospId));
		c.add(Restrictions.eq("isDelete", 0));
		
		if(operate == 1){
			c.add(Restrictions.eq("pushStatus", 1));
			c.add(Restrictions.eq("isBanner", 0));
		}else if(operate == 2){
			c.add(Restrictions.eq("pushStatus", 0));
		}else if(operate == 3){
			c.add(Restrictions.eq("isBanner", 1));
		}
		
		if(StringUtils.isNotEmpty(keywords)) {
			c.add(Restrictions.like("title", keywords, MatchMode.ANYWHERE));
		}
		c.addOrder(Order.desc("addTime"));
		Page<NewsInformationMessage> list = findPageByCriteria(page, c);
		return list;
	}

	@Override
	public boolean checkBannerCount(Integer id, Integer hospitalId) {
		try {
			Criteria c = createCriteria();
			c.add(Restrictions.eq("hospitalInfo.id", hospitalId));
			c.add(Restrictions.eq("isBanner", Consts.TRUE));
			c.add(Restrictions.eq("pushStatus", Consts.TRUE));
			c.add(Restrictions.eq("isDelete", Consts.FALSE));
			c.setProjection(Projections.rowCount());
			long count = (Long) c.uniqueResult();
			if(count >= 6){
				return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return true;
		}
	}

}
