package com.jumper.hospital.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.jumper.hospital.dao.AppIndexDao;
import com.jumper.hospital.entity.AppIndex;
/**
 * APP首页维护DaoImpl
 * @author qinxiaowei
 *
 */
@Repository
public class AppIndexDaoImpl extends BaseDaoImpl<AppIndex, Integer> implements AppIndexDao {
	
	/**
	 * 查询所有app首页数据
	 * @param showPosition 显示位置
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<AppIndex> findAppIndex() {
		String hql = "from com.jumper.hospital.entity.AppIndex order by sort asc";
		Query query = this.getSession().createQuery(hql);
		//query.setInteger(0, showPosition);
		return query.list();
	}
	
	/**
	 * 查询出最后一条数据
	 * @param showPosition 位置
	 * @return
	 */
	public AppIndex findLastAppIndex(int showPosition) {
		String hql = "from com.jumper.hospital.entity.AppIndex where showPosition=? order by sort desc";
		Query query = this.getSession().createQuery(hql);
		query.setInteger(0, showPosition);
		query.setFirstResult(0);  
        query.setMaxResults(1); 
		return (AppIndex) query.list().get(0);
	}
	
	/**
	 * 查询显示个数
	 * @param showPosition
	 * @return
	 */
	public int findIsShowCount(int showPosition) {
		String hql = "select count(*) from com.jumper.hospital.entity.AppIndex where showPosition=? and isShow=1";
		Query query = this.getSession().createQuery(hql);
		query.setInteger(0, showPosition);
		return Integer.parseInt(query.uniqueResult().toString());
	}
	
	/**
	 * 查询所有省份
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> findProvince() {
		String sql = "select id, proName from province where abbrevation='CHN'";
		Query query = this.getSession().createSQLQuery(sql);
		List<Object[]> list = query.list();
		return list;
	}
	
	/**
	 * 查询对应省份下所有城市
	 * @param proId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> findCity(int proId) {
		String sql = "select id, cityName from city where proId=?";
		Query query = this.getSession().createSQLQuery(sql);
		query.setInteger(0, proId);
		List<Object[]> list = query.list();
		return list;
	}
	
	/**
	 * 查询对应城市下所有医院
	 * @param proId
	 * @param cityId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> findHospitalInfo(int proId, int cityId) {
		String sql = "select id, name from hospital_info where province_id=? and city_id=?";
		Query query = this.getSession().createSQLQuery(sql);
		query.setInteger(0, proId);
		query.setInteger(1, cityId);
		List<Object[]> list = query.list();
		return list;
	}
	
	/**
	 * 查询医院信息
	 * @param hospitalId
	 * @return
	 */
	public Object[] findHospitalInfoById(int hospitalId) {
		String sql = "select province_id, city_id from hospital_info where id=?";
		Query query = this.getSession().createSQLQuery(sql);
		query.setInteger(0, hospitalId);
		Object[] list = (Object[])query.list().get(0);
		return list;
	}
}
