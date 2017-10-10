package com.jumper.hospital.service;

import java.util.List;
import java.util.Map;

import com.jumper.hospital.entity.AppIndex;

/**
 * APP首页维护Service
 * @author qinxiaowei
 *
 */
public interface AppIndexService extends BaseService<AppIndex, Integer> {

	/**
	 * 查询所有app首页数据
	 * @param showPosition 显示位置
	 * @return
	 */
	public List<AppIndex> findAppIndex();
	
	/**
	 * 添加
	 * @param app
	 */
	public void saveAppIndex(AppIndex app);
	
	/**
	 * 查询出最后一条数据
	 * @param showPosition 位置
	 * @return
	 */
	public AppIndex findLastAppIndex(int showPosition);
	
	/**
	 * 删除数据
	 * @param id
	 */
	public boolean deleteAppIndex(int id);
	
	/**
	 * 通过id查询数据
	 * @param id
	 * @return
	 */
	public AppIndex findAppIndexById(int id);
	
	/**
	 * 修改数据
	 * @param ai
	 * @return
	 */
	public boolean updateAppIndex(AppIndex ai);
	
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
	public List<Map<String, Object>> findProvince();
	
	/**
	 * 查询对应省份下所有城市
	 * @param proId
	 * @return
	 */
	public List<Map<String, Object>> findCity(int proId);
	
	/**
	 * 查询对应城市下所有医院
	 * @param proId
	 * @param cityId
	 * @return
	 */
	public List<Map<String, Object>> findHospitalInfo(int proId, int cityId);
	
	/**
	 * 查询医院信息
	 * @param hospitalId
	 * @return
	 */
	public Object[] findHospitalInfoById(int hospitalId);
}
