package com.jumper.hospital.service;

/**
 * 用户Service
 * @author rent
 * @data 2015-10-20
 */
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.jumper.hospital.base.Page;
import com.jumper.hospital.entity.Hospital;
import com.jumper.hospital.entity.UserExtraInfo;
import com.jumper.hospital.entity.UserInfo;
import com.jumper.hospital.vo.VoUserInfo;

public interface UserInfoService extends BaseService<UserInfo, Integer> {

	/**
	 * 查询医院的绑定用户
	 * 
	 * @param hospitalId
	 *            医院ID
	 * @return
	 */
	public Page<VoUserInfo> findBindUserListByHospital(Page<UserInfo> page, Integer hospitalId, String searchKey,
			Integer type);

	/**
	 * 查询该医院关联用户的所有孕周
	 * 
	 * @param id
	 * @return
	 */
	public List<Integer> findWeekList(Integer id);

	/**
	 * 根据孕周查询用户
	 * 
	 * @param pregnantWeek
	 * @return
	 */
	public List<Integer> findUserIdsByPregnantWeek(int pregnantWeek, int hospitalId, String flags);

	/**
	 * 查询该医院关联用户的所有宝宝周
	 * 
	 * @param id
	 * @return
	 */
	public List<Integer> findBabyList(Integer id);

	/**
	 * 根据预产期获取孕周
	 * 
	 * @param id
	 * @return
	 */
	public List<Integer> getPregnantWeeks(Integer id);

	/**
	 * 根据宝宝生日获取宝宝的年龄
	 * 
	 * @param id
	 * @return
	 */
	public List<String> getBabyAges(Integer id);

	/**
	 * 获取已绑定该医院的所有用户的id和预产期或者宝宝生日
	 * 
	 * @param id
	 * @param flag
	 * @return
	 */
	public Map<Integer, Date> findUserIdsAndDate(Integer id, int flag);

	/**
	 * 根据联系电话查询用户
	 * 
	 * @param phone
	 * @return
	 */
	public UserInfo findByContactPhone(String phone);

	public UserInfo findUserInfoById(Integer id);

	public UserExtraInfo findUserExtra(Integer id);

	public UserExtraInfo findUserExtra(String phone);

	public int newUserCount(String startTime, String endTime, Integer id);

	public int allUserCount(Integer id);

	public int todayNewUserCount(Integer id);
	
	public List<Hospital> findAllHospitalInfo(String hospitalName);
}
