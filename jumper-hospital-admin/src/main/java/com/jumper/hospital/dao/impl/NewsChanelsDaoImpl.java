package com.jumper.hospital.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;

import com.jumper.hospital.base.Page;
import com.jumper.hospital.dao.NewsChanelsDao;
import com.jumper.hospital.entity.NewsChanels;

@Repository
public class NewsChanelsDaoImpl extends BaseDaoImpl<NewsChanels, Integer> implements NewsChanelsDao {

	@SuppressWarnings("unchecked")
	@Override
	public Page<NewsChanels> findChanelsByPageHospital(Page<NewsChanels> page, Integer hospitalId) {
		try {
			String sqlStr = "select c.id id,c.chanel_name chanelName,c.is_default_sub isDefaultSub,c.order_by orderBy,c.sub_num subNum,c.state state,count(i.id) newsNum " +
					"from news_chanels c left join news_information i on(c.id=i.channel_id) where c.hospital_id=? group by c.id order by c.order_by desc limit ?,?;";
			String countSql = "select count(id) from news_chanels where hospital_id="+hospitalId;
			Integer offset = page.getFirst() - 1;
			SQLQuery query = createSqlQuery(sqlStr, new Object[]{hospitalId, offset, page.getPageSize()});
			query.addScalar("id", StandardBasicTypes.INTEGER);
			query.addScalar("chanelName", StandardBasicTypes.STRING);
			query.addScalar("isDefaultSub", StandardBasicTypes.BOOLEAN);
			query.addScalar("orderBy", StandardBasicTypes.INTEGER);
			query.addScalar("subNum", StandardBasicTypes.INTEGER);
			query.addScalar("state", StandardBasicTypes.BOOLEAN);
			query.addScalar("newsNum", StandardBasicTypes.INTEGER);
			query.setResultTransformer(Transformers.aliasToBean(NewsChanels.class));
			List<NewsChanels> list = query.list();
			int count = executeCountSql(countSql);
			page.setResult(list);
			page.setTotalCount(count);
			return page;
		} catch (Exception e) {
			e.printStackTrace();
			return page;
		}
	}

	@Override
	public Integer findMaxOrderByByHospital(Integer hospitalId, String order) {
		Criteria c = createCriteria();
		c.add(Restrictions.eq("hospitalId", hospitalId));
		if(order.equalsIgnoreCase("asc")){
			c.setProjection(Projections.min("orderBy"));
		}else{
			c.setProjection(Projections.max("orderBy"));
		}
		Integer orderBy = (Integer) c.uniqueResult();
		return orderBy;
	}

	@Override
	public long findChanelNumByHospital(Integer hospitalId) {
		Criteria c = createCriteria();
		c.add(Restrictions.eq("hospitalId", hospitalId));
		c.setProjection(Projections.rowCount());
		long count = (Long) c.uniqueResult();
		return count;
	}

	@Override
	public long getDefaultSubNum(Integer hospitalId) {
		Criteria c = createCriteria();
		c.add(Restrictions.eq("hospitalId", hospitalId));
		c.add(Restrictions.eq("isDefaultSub", true));
		c.setProjection(Projections.rowCount());
		long defaultCount = (Long) c.uniqueResult();
		return defaultCount;
	}

	@Override
	public long getShowStateNum(Integer hospitalId) {
		Criteria c = createCriteria();
		c.add(Restrictions.eq("hospitalId", hospitalId));
		c.add(Restrictions.eq("state", true));
		c.setProjection(Projections.rowCount());
		long defaultCount = (Long) c.uniqueResult();
		return defaultCount;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<NewsChanels> findChanelByHospitalId(Integer hospitalId) {
		Criteria c = createCriteria();
		c.add(Restrictions.eq("hospitalId", hospitalId));
		c.addOrder(Order.desc("orderBy"));
		return c.list();
	}

}
