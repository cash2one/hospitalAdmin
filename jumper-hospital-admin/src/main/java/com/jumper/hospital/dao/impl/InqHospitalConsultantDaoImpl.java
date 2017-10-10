
package com.jumper.hospital.dao.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.jumper.hospital.base.Page;
import com.jumper.hospital.dao.InqHospitalConsultantDao;
import com.jumper.hospital.entity.HospitalConsServiceOrder;
import com.jumper.hospital.entity.InqHospitalConsultant;

@Repository
public class InqHospitalConsultantDaoImpl extends BaseDaoImpl<InqHospitalConsultant, Integer> implements
		InqHospitalConsultantDao {

	@Override
	public Page<InqHospitalConsultant> findTodayConsultList(Page<InqHospitalConsultant> page,Integer id ) {
		Criteria c = createCriteria();
		c.add(Restrictions.eq("isDelete", 0));
		c.add(Restrictions.eq("evaluate", 0));
		c.addOrder(Order.desc("addTime"));
		Object []status = {2,3,4};
		c.add(Restrictions.in("status", status));
		c.add(Restrictions.eq("hospitalInfo.id", id));
		c.createAlias("userInfo","um");
		c.createAlias("userInfo.userExitInfo","u");
		c.add(Restrictions.and(Restrictions.isNotNull("content"), Restrictions.not(Restrictions.eq("content", ""))));
		/*String sqlsize ="select * from (select t1.* from inq_hospital_consultant t1 left join hospital_cons_service_order t2 on "+
				" (t1.pay_hospital_id = t2.id or t1.pay_hospital_id = 0)  LEFT JOIN hospital_info t3 on t3.id = t1.hospital_id where t2.`status` = 1 and t1.is_delete = 0 "
				+ "and t1.evaluate = 0  and t1.content <> '' and t1.content is not NULL and t3.id = ? and t1.`status` in (2,3,4) group by t1.id ) t";
		String sql ="select * from (select t1.* from inq_hospital_consultant t1 left join hospital_cons_service_order t2 on "+
				" (t1.pay_hospital_id = t2.id or t1.pay_hospital_id = 0) LEFT JOIN hospital_info t3 on t3.id = t1.hospital_id where t2.`status` = 1 and "
				+ "t1.is_delete = 0 and t1.evaluate = 0  and t1.content <> '' and t1.content is not NULL"
				+ " and t3.id = ? and t1.`status` in (2,3,4)  group by t1.id order by t1.add_time desc ) t limit ? , ? ";
		Query query1 = this.getSession().createSQLQuery(sqlsize);
		
		 query1.setInteger(0, id);
			 int i = query1.list().size();
		Query query = this.getSession().createSQLQuery(sql).addEntity(InqHospitalConsultant.class);
		query.setInteger(0, id);
		query.setInteger(1, page.getPageNo()-1);
		query.setInteger(2, page.getPageSize());
		page.setResult(query.list());
		page.setTotalCount(i);
		return page;*/
		return findPageByCriteria(page, c);
	}
	
	@SuppressWarnings("unchecked")
	public List<HospitalConsServiceOrder> findConsultListing(){
		String sql = "select * from hospital_cons_service_order where status = 1";
		Query query = this.getSession().createSQLQuery(sql).addEntity(HospitalConsServiceOrder.class);
		return (List<HospitalConsServiceOrder>) query.list();
	}
	@Override
	public Page<InqHospitalConsultant> findAllConsultList(
			Page<InqHospitalConsultant> page, Integer id) {
		Criteria c = createCriteria();
		//未删除的
		c.add(Restrictions.eq("isDelete", 0));
		c.add(Restrictions.or(Restrictions.eq("evaluate", 1), Restrictions.eq("status", 5)));
		//当前医院的
		c.add(Restrictions.eq("hospitalInfo.id", id));
		c.add(Restrictions.isNotNull("content"));
		c.addOrder(Order.desc("addTime"));
		return findPageByCriteria(page, c);
		
		/*String sqlsize = "select * from (select t1.* from inq_hospital_consultant t1 left join hospital_cons_service_order t2 on"
				+ " (t1.pay_hospital_id = t2.id or t1.pay_hospital_id = 0) LEFT JOIN hospital_info t3 on t3.id = t1.hospital_id "
				+ "where t2.`status` =4 and t1.is_delete = 0 and t1.evaluate = 0  and t1.content <> '' and t1.content is not NULL	 "
				+ "and t3.id = ? and t1.`status` = 5  group by t1.id order by t1.add_time desc ) t ";
		String sql ="select * from (select t1.* from inq_hospital_consultant t1 left join hospital_cons_service_order t2 on"
				+ " (t1.pay_hospital_id = t2.id or t1.pay_hospital_id = 0) LEFT JOIN hospital_info t3 on t3.id = t1.hospital_id "
				+ "where t2.`status` =4 and t1.is_delete = 0 and t1.evaluate = 0  and t1.content <> '' and t1.content is not NULL	 "
				+ "and t3.id = ? and t1.`status` = 5  group by t1.id order by t1.add_time desc ) t limit ? , ? ";
		
		Query query1 = this.getSession().createSQLQuery(sqlsize);
		
		 query1.setInteger(0, id);
			 int i = query1.list().size();
		Query query = this.getSession().createSQLQuery(sql).addEntity(InqHospitalConsultant.class);
		query.setInteger(0, id);
		query.setInteger(1, page.getPageNo()-1);
		query.setInteger(2, page.getPageSize());
		page.setResult(query.list());
		page.setTotalCount(i);
		return page;*/
	}

	@Override
	public Page<InqHospitalConsultant> findTodayConsultListByConds(
			Page<InqHospitalConsultant> page, Integer id, String searchKey) {
		Criteria c = createCriteria();
		c.add(Restrictions.eq("isDelete", 0));
		c.add(Restrictions.eq("evaluate", 0));
		c.addOrder(Order.desc("addTime"));
		Object []status = {2,3,4};
		c.add(Restrictions.in("status", status));
		c.add(Restrictions.eq("hospitalInfo.id", id));
		c.createAlias("userInfo","um");
		c.createAlias("userInfo.userExitInfo","u");
		if(!StringUtils.isEmpty(searchKey)){
			c.add(Restrictions.or(Restrictions.like("u.realName",searchKey,MatchMode.ANYWHERE), Restrictions.like("um.mobile",searchKey,MatchMode.ANYWHERE)));
		}
		c.add(Restrictions.and(Restrictions.isNotNull("content"), Restrictions.not(Restrictions.eq("content", ""))));
		//c.add(Restrictions.between("addTime", TimeUtils.getCurrentStartTime(0, new Date()), TimeUtils.getCurrentEndTime(0, new Date())));
		return findPageByCriteria(page, c);
		/*String sqlsize =" select * from (select t1.*,t4.real_name from inq_hospital_consultant t1 left join hospital_cons_service_order t2 on (t1.pay_hospital_id = t2.id or t1.pay_hospital_id = 0) "+
				" LEFT JOIN hospital_info t3 on t3.id = t1.hospital_id left join user_extra_info t4 on t4.user_id = t1.user_id where t2.`status` = 1 "+ 
				" and t1.is_delete = 0 and t1.evaluate = 0  and t1.content <> '' and t1.content is not NULL and t1.`status` in (2,3,4)  and t3.id = ? ";
		String sql = "select * from (select t1.*,t4.real_name from inq_hospital_consultant t1 left join hospital_cons_service_order t2 on  (t1.pay_hospital_id = t2.id or t1.pay_hospital_id = 0) "+
				" LEFT JOIN hospital_info t3 on t3.id = t1.hospital_id  left join user_extra_info t4 on t4.user_id = t1.user_id where t2.`status` = 1 "+ 
				"and t1.is_delete = 0 and t1.evaluate = 0  and t1.content <> '' and t1.content is not NULL  and t3.id = ? and t1.`status` in (2,3,4) ";
		

		if(searchKey != null && searchKey != ""){
			sqlsize+= " and (t4.real_name like ? or  t4.contact_phone like  ?)";
		}
		sqlsize+= " group by t1.id ) t";
		Query query1 = this.getSession().createSQLQuery(sqlsize);
		
		 query1.setInteger(0, id);
		 if(searchKey != null && searchKey != ""){
			 query1.setString(1, "%"+ searchKey +"%");
			 query1.setString(2, "%"+ searchKey +"%");
		 }
		 
		
		 int i = query1.list().size();
			if(searchKey != null && searchKey != ""){
				sql+= " and (t4.real_name like ? or  t4.contact_phone like  ?) ";
			}
			sql+= " group by t1.id order by t1.add_time desc  ) t limit ? , ?";
		Query query = this.getSession().createSQLQuery(sql).addEntity(InqHospitalConsultant.class);
		query.setInteger(0, id);
		 if(searchKey != null && searchKey != ""){
			 query.setString(1, "%"+ searchKey +"%");
			 query.setString(2, "%"+ searchKey +"%");
			 query.setInteger(3, page.getPageNo()-1);
				query.setInteger(4, page.getPageSize());
		 }else{
			 query.setInteger(1, page.getPageNo()-1);
			query.setInteger(2, page.getPageSize());
		 }
		
		page.setResult(query.list());
		page.setTotalCount(i);
		return page;*/
	}

	@Override
	public Page<InqHospitalConsultant> findAllConsultListByConds(Page<InqHospitalConsultant> page,
			Integer id, String searchKey,String visitType) {
		Criteria c = createCriteria();
		//未删除的
		c.add(Restrictions.eq("isDelete", 0));
		c.add(Restrictions.or(Restrictions.eq("evaluate", 1), Restrictions.eq("status", 5)));
		//当前医院的
		c.add(Restrictions.eq("hospitalInfo.id", id));
		c.createAlias("userInfo","um");
		c.createAlias("userInfo.userExitInfo","u");
		if(!StringUtils.isEmpty(searchKey)){
			c.add(Restrictions.or(Restrictions.like("u.realName",searchKey,MatchMode.ANYWHERE), Restrictions.like("um.mobile",searchKey,MatchMode.ANYWHERE)));
		}
		if(StringUtils.isNotEmpty(visitType)){
			int type = Integer.parseInt(visitType);
			if(type==1){
				c.add(Restrictions.or(Restrictions.eq("payHospitalId", 0),Restrictions.eq("payHospitalId", null)));
			}else if(type==2){
				//c.add(Restrictions.eq("payHospitalId", 1));
				c.add(Restrictions.and(Restrictions.isNotNull("payHospitalId"), Restrictions.gt("payHospitalId", 0)));
			}
		}
		c.add(Restrictions.isNotNull("content"));
		c.addOrder(Order.desc("addTime"));
		return findPageByCriteria(page, c);
		
		/*String sqlsize =" select * from (select t1.*,t4.real_name from inq_hospital_consultant t1 left join hospital_cons_service_order t2 on (t1.pay_hospital_id = t2.id or t1.pay_hospital_id = 0) "+
				" LEFT JOIN hospital_info t3 on t3.id = t1.hospital_id left join user_extra_info t4 on t4.user_id = t1.user_id where t2.`status` = 5 "+ 
				" and t1.is_delete = 0 and t1.evaluate = 0  and t1.content <> '' and t1.content is not NULL and t1.`status` in (2,3,4)  and t3.id = ? ";
		String sql = "select * from (select t1.*,t4.real_name from inq_hospital_consultant t1 left join hospital_cons_service_order t2 on  (t1.pay_hospital_id = t2.id or t1.pay_hospital_id = 0) "+
				" LEFT JOIN hospital_info t3 on t3.id = t1.hospital_id  left join user_extra_info t4 on t4.user_id = t1.user_id where t2.`status` = 5 "+ 
				"and t1.is_delete = 0 and t1.evaluate = 0  and t1.content <> '' and t1.content is not NULL  and t3.id = ? and t1.`status` in (2,3,4) ";
		
		if(searchKey != null && searchKey != ""){
			sqlsize+= " and (t4.real_name like ? or  t4.contact_phone like  ?)";
		}
		sqlsize+= " group by t1.id ) t";
		Query query1 = this.getSession().createSQLQuery(sqlsize);
		
		 query1.setInteger(0, id);
		 if(searchKey != null && searchKey != ""){
			 query1.setString(1, "%"+ searchKey +"%");
			 query1.setString(2, "%"+ searchKey +"%");
		 }
		 
		
		 int i = query1.list().size();
			if(searchKey != null && searchKey != ""){
				sql+= " and (t4.real_name like ? or  t4.contact_phone like  ?) ";
			}
			sql+= " group by t1.id order by t1.add_time desc  ) t limit ? , ?";
		Query query = this.getSession().createSQLQuery(sql).addEntity(InqHospitalConsultant.class);
		query.setInteger(0, id);
		 if(searchKey != null && searchKey != ""){
			 query.setString(1, "%"+ searchKey +"%");
			 query.setString(2, "%"+ searchKey +"%");
			 query.setInteger(3, page.getPageNo()-1);
				query.setInteger(4, page.getPageSize());
		 }else{
			 query.setInteger(1, page.getPageNo()-1);
			query.setInteger(2, page.getPageSize());
		 }
		
		page.setResult(query.list());
		page.setTotalCount(i);
		return page;*/
	}

	@Override
	public Page<InqHospitalConsultant> findAllConsultListByType(Page<InqHospitalConsultant> page,
			Integer id, String visitType) {
		Criteria c = createCriteria();
		//未删除的
		c.add(Restrictions.eq("isDelete", 0));
		c.add(Restrictions.or(Restrictions.eq("evaluate", 1), Restrictions.eq("status", 5)));
		//当前医院的
		c.add(Restrictions.eq("hospitalInfo.id", id));
		c.createAlias("userInfo","u");
		if(StringUtils.isNotEmpty(visitType)){
			int type = Integer.parseInt(visitType);
			if(type==1){
				c.add(Restrictions.or(Restrictions.eq("payHospitalId", 0),Restrictions.eq("payHospitalId", null)));
			}else if(type==2){
				//c.add(Restrictions.eq("payHospitalId", 1));
				c.add(Restrictions.and(Restrictions.isNotNull("payHospitalId"), Restrictions.gt("payHospitalId", 0)));
			}
		}
		c.add(Restrictions.isNotNull("content"));
		c.addOrder(Order.desc("addTime"));
		return findPageByCriteria(page, c);
	}

	@Override
	public InqHospitalConsultant getInqConsultantByConOrderId(Integer conId) {
		Criteria c = createCriteria();
		c.add(Restrictions.eq("payHospitalId", conId));
		c.setMaxResults(1);
		return (InqHospitalConsultant) c.uniqueResult();
	}

	@Override
	public int updateStatus(Integer id,Integer status) {
		String sql = "update inq_hospital_consultant set status = ? where id = ?";
		Query query = this.getSession().createSQLQuery(sql);
		query.setInteger(0, status);
		query.setInteger(1, id);
		return query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<InqHospitalConsultant> findTodayAllConsultList() {
		String sql = "select * from inq_hospital_consultant where status <> 5";
		Query query = this.getSession().createSQLQuery(sql).addEntity(InqHospitalConsultant.class);
		return query.list();
	}
	

}
