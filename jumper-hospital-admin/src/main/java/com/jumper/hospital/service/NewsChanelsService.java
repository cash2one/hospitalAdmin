package com.jumper.hospital.service;

import java.util.List;

import com.jumper.hospital.base.Page;
import com.jumper.hospital.entity.NewsChanels;
import com.jumper.hospital.vo.VOBaseResult;
import com.jumper.hospital.vo.VONewsChanelAdd;

public interface NewsChanelsService extends BaseService<NewsChanels, Integer> {

	/**
	 * 查询医院所添加的频道信息
	 * @param page 分页信息
	 * @param hospitalId 医院ID
	 * @return page
	 */
	public Page<NewsChanels> findChanelsByPageHospital(Page<NewsChanels> page, Integer hospitalId);
	/**
	 * 编辑频道信息
	 * @param id
	 * @param chanelName
	 * @param hospitalId
	 * @return
	 */
	public VONewsChanelAdd editChanelInfo(Integer id, String chanelName, Integer hospitalId);
	/**
	 * 查询排序最大或最小值
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
	 * 更改频道默认订阅或者显示状态属性
	 * @param id
	 * @param operate
	 * @param type
	 * @param hospitalId
	 * @return
	 */
	public VOBaseResult changeChanelState(Integer id, Integer operate, Integer type, Integer hospitalId);
	/**
	 * 查找医院频道信息
	 * @param hospitalId
	 * @return
	 */
	public List<NewsChanels> findChanelByHospitalId(Integer hospitalId);
}
