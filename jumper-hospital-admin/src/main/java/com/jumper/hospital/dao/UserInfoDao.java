package com.jumper.hospital.dao;
/**
 * APP用户Dao
 * @author rent
 * @date 2015-09-21
 */
import java.util.Date;
import java.util.List;

import com.jumper.hospital.base.Page;
import com.jumper.hospital.entity.Hospital;
import com.jumper.hospital.entity.MonitorUserInfo;
import com.jumper.hospital.entity.UserExtraInfo;
import com.jumper.hospital.entity.UserInfo;

public interface UserInfoDao extends BaseDao<UserInfo, Integer> {

	/**
	 * 查询已绑定改医院的用户列表
	 * @param hospitalId 医院ID
	 * @return
	 */
	public Page<UserInfo> findUserListByHospital(Page<UserInfo> page, Integer hospitalId, String searchKey, Integer type);

	/**
	 * 获取该医院关联用户的所有孕周
	 * @param id
	 * @return
	 */
	public List<Integer> findWeekList(Integer id);

	/**
	 * 根据孕周获取用户
	 * @param pregnantWeek
	 * @return
	 */
	public List<Integer> findUserIdsByPregnantWeek(int pregnantWeek,int hospitalId,String flags);

	/**
	 * 获取该医院关联用户的所有宝宝周
	 * @param id
	 * @return
	 */
	public List<Integer> findBabyList(Integer id);

	/**
	 * 根据预产期获取孕周
	 * @param id
	 * @return
	 */
	public List<Date> getExpectedDateOfPregnant(Integer id);

	/**
	 * 根据宝宝生日获取宝宝年龄
	 * @param id
	 * @return
	 */
	public List<Date> getBabyBirthdays(Integer id);

	/**
	 * 获取绑定该医院的用户的id和预产期或者宝宝生日
	 * @param id
	 * @param flag
	 * @return
	 */
	public List<Object> findUserIdsAndDate(Integer id, int flag);
	/**
	 * 根据ID查询用户
	 * @param ids
	 * @return
	 */
	public List<UserInfo> findByIds(String ids);
	/**
	 * 根据手机号码查询用户
	 * @param phone
	 * @return
	 */
	public UserInfo findByContactPhone(String phone);
	/**
	 * 根据注册手机号码查询用户
	 * @param mobile
	 * @return
	 */
	public UserInfo findByMobile(String mobile);
	/**
	 * 根据院内监测用户创建APP用户
	 * @param monitorUserInfo
	 * @return
	 */
	public UserInfo createInsideAppUser(MonitorUserInfo monitorUserInfo,String edcDate);
	
	
	public UserInfo  findByUserId(Integer id);
	
	//根据userId查询用户扩展表的信息
	public UserExtraInfo findUserExtra(Integer id);
	
	//根据电话号码查询用户扩展信息
	public UserExtraInfo findUserExtra(String phone);
	
	public int newUserCount(String startTime , String endTime ,Integer id);
	
	
	public int allUserCount(Integer id);
	
	public int allUserCountbyIos(Integer id);
	
	public int todayNewUserCount(Integer id);
	
	public int todayNewUserCountbyIos(Integer id);
	
	public int newUserCountbyIos(String startTime, String endTime,Integer id);
	
	public List<Hospital> findAllHospitalInfo(String hospitalName);
}
