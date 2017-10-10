package com.jumper.hospital.dao;

import java.util.List;

import com.jumper.hospital.base.Page;
import com.jumper.hospital.entity.NewsChanels;

public interface NewsChanelsDao extends BaseDao<NewsChanels, Integer> {

	/**
	 * 查询医院所添加的频道信息
	 * @param page 分页信息
	 * @param hospitalId 医院ID
	 * @return page
	 */
	public Page<NewsChanels> findChanelsByPageHospital(Page<NewsChanels> page, Integer hospitalId);
	/**
	 * 查找排序最大值与最小值
	 * @param hospitalId
	 * @param order
	 * @return
	 */
	public Integer findMaxOrderByByHospital(Integer hospitalId, String order);
	/**
	 * 根据医院查询频道数量
	 * @param hospitalId
	 * @return
	 */
	public long findChanelNumByHospital(Integer hospitalId);
	/**
	 * 获取默认订阅的频道数
	 * @param hospitalId
	 * @return
	 */
	public long getDefaultSubNum(Integer hospitalId);
	/**
	 * 获取状态为显示的频道数
	 * @param hospitalId
	 * @return
	 */
	public long getShowStateNum(Integer hospitalId);
	/**
	 * 查找医院频道信息
	 * @param hospitalId
	 * @return
	 */
	public List<NewsChanels> findChanelByHospitalId(Integer hospitalId);
}
