package com.jumper.hospital.dao.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;

import com.jumper.hospital.base.Page;
import com.jumper.hospital.dao.NewsInformationDao;
import com.jumper.hospital.entity.NewsInformation;

@Repository
public class NewsInformationDaoImpl extends BaseDaoImpl<NewsInformation, Integer> implements NewsInformationDao {

	@Override
	public Integer deleteByChanelId(Integer chanelId) {
		String sql = "delete from news_information where channel_id=?";
		SQLQuery sqlQuery = createSqlQuery(sql, chanelId);
		int result = sqlQuery.executeUpdate();
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<NewsInformation> search(Integer publish, Integer banner,
			String searchKey, Integer chanelId, Integer periodId, Integer belong, Page<NewsInformation> page, Integer hospitalId) {
		try {
			String sql = "";
			if(publish == 0 || banner == 0){
				sql = "from news_information n left join news_chanels c on n.channel_id=c.id left join news_information_comments nc on nc.news_information_id=n.id where ";
			}else{
				sql = "from news_information n inner join news_chanels c on n.channel_id=c.id left join news_information_comments nc on nc.news_information_id=n.id" +
						" inner join pregnant_course_news pn on n.id=pn.news_id inner join pregnant_course_index i on pn.pregnant_id=i.id where ";
			}
			StringBuffer sqlBuffer = new StringBuffer(sql);
			String publishSql = publish == null || publish == 0 ? "is_publish=0 " : "is_publish=1 ";
			String searchSql = StringUtils.isNotEmpty(searchKey) ? "and n.title like '%"+searchKey+"%' " : "";
			String chanelSql = chanelId != null && chanelId != 0 ? "and n.channel_id="+chanelId+" " : "";
			String periodSql = periodId != null && periodId != 0 ? (banner == 1 && publish == 1 ? "and i.pregnant_type="+periodId+" " : "and n.period like '%"+periodId+"%' ") : "";
			String belogSql = publish != null && publish == 1 ? "and n.hospital_id="+hospitalId : "and n.hospital_id="+belong;
			String order = publish == 0 ? " order by n.add_time desc" : (banner == 0 ? " order by n.publish_time desc" : " order by n.top desc");
			String orderBySql = " group by n.id"+ order;
			String pageSql = " limit "+(page.getFirst() - 1)+", "+page.getPageSize()+";";
			
			if(publish == 0 && belong == 49){
				sqlBuffer.append("1=1 ");
			}else{
				sqlBuffer.append(publishSql);
			}
			sqlBuffer.append(searchSql);
			sqlBuffer.append(chanelSql);
			sqlBuffer.append(periodSql);
			sqlBuffer.append(belogSql);
			
			String countSql = "select count(*) from (select n.id "+sqlBuffer.toString()+" group by n.id) b;";
			sqlBuffer.append(orderBySql);
			sqlBuffer.append(pageSql);
			String querySql = "select n.id id,n.title title,n.channel_id channelId,c.chanel_name chanelName,n.period period,n.image_url imageUrl," +
					"n.clicks clicks,n.share_num shareNum,count(nc.id) commentNum,n.publish_time publishTime,n.add_time addTime "+sqlBuffer.toString();
			
			SQLQuery query = createSqlQuery(querySql, new Object[]{});
			query.addScalar("id", StandardBasicTypes.INTEGER);
			query.addScalar("title", StandardBasicTypes.STRING);
			query.addScalar("channelId", StandardBasicTypes.INTEGER);
			query.addScalar("chanelName", StandardBasicTypes.STRING);
			query.addScalar("period", StandardBasicTypes.STRING);
			query.addScalar("imageUrl", StandardBasicTypes.STRING);
			query.addScalar("clicks", StandardBasicTypes.INTEGER);
			query.addScalar("shareNum", StandardBasicTypes.INTEGER);
			query.addScalar("commentNum", StandardBasicTypes.INTEGER);
			query.addScalar("publishTime", StandardBasicTypes.TIMESTAMP);
			query.addScalar("addTime", StandardBasicTypes.TIMESTAMP);
			query.setResultTransformer(Transformers.aliasToBean(NewsInformation.class));
			
			List<NewsInformation> list = query.list();
			int count = executeCountSql(countSql);
			page.setResult(list);
			page.setTotalCount(count);
			return page;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void clearSession() {
		getSession().clear();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> findIdsByChanelId(Integer chanelId) {
		String sql = "select n.id id from news_information n where n.channel_id=?";
		SQLQuery query = createSqlQuery(sql, new Object[]{chanelId});
		List<Object> list = query.list();
		return list;
	}
	
	
}
