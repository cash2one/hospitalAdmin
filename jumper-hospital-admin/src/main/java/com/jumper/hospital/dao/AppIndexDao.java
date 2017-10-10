package com.jumper.hospital.dao;

import java.util.List;

import com.jumper.hospital.entity.AppIndex;

/**
 * APP首页维护Dao
 * @author qinxiaowei
 *
 */
public interface AppIndexDao extends BaseDao<AppIndex, Integer> {
	
	/**
	 * 查询所有app首页数据
	 * @param showPosition 显示位置
	 * @return
	 */
	public List<AppIndex> findAppIndex();
	
	/**
	 * 查询出最后一条数据
	 * @param showPosition 位置
	 * @return
	 */
	public AppIndex findLastAppIndex(int showPosition);
	
	/**
	 * 查询显示个数
	 * @param showPosition
	 * @return
	 */
	public int findIsShowCount(int showPosition);
	
	/**
	 * 查询所有省份
	 * @return
	 */
	public List<Object[]> findProvince();
	
	/**
	 * 查询对应省份下所有城市
	 * @param proId
	 * @return
	 */
	public List<Object[]> findCity(int proId);
	
	/**
	 * 查询对应城市下所有医院
	 * @param proId
	 * @param cityId
	 * @return
	 */
	public List<Object[]> findHospitalInfo(int proId, int cityId);
	
	/**
	 * 查询医院信息
	 * @param hospitalId
	 * @return
	 */
	public Object[] findHospitalInfoById(int hospitalId);
}
