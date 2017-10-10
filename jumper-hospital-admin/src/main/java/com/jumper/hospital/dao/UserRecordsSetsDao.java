package com.jumper.hospital.dao;
/**
 * 用户测试记录Dao
 * @author rent
 * @date 2015-09-21
 */
import java.util.List;

import com.jumper.hospital.entity.UserRecordsSets;

public interface UserRecordsSetsDao extends BaseDao<UserRecordsSets, Integer> {

	/**
	 * 胎心数据查询，常规监控，取当天数据且监测10分钟以上的，这里只查询出当前的数据
	 * @param userId
	 * @return
	 */
	public List<Integer> findHeartrateRecord(Integer userId);
	
	/**
	 * 胎动数据查询，常规监控，取当天监测的至多3条数据且都在1个小时以上，这里只查询出当前数据
	 * @param userId
	 * @return
	 */
	public List<Integer> findFetalmoveRecord(Integer userId);
	
	/**
	 * 查询用户血糖数据，取最近一天的记录中的最大值
	 * @param userId
	 * @return
	 */
	public List<Integer> findSugarRecord(Integer userId);
	
}
