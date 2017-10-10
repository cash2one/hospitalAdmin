package com.jumper.hospital.service.impl;

/**
 * UserInfoService实现类
 * @author rent
 * @date 2015-10-20
 */
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jumper.hospital.Comparator.MyComparator;
import com.jumper.hospital.base.Page;
import com.jumper.hospital.dao.BaseDao;
import com.jumper.hospital.dao.UserInfoDao;
import com.jumper.hospital.entity.Hospital;
import com.jumper.hospital.entity.UserExtraInfo;
import com.jumper.hospital.entity.UserInfo;
import com.jumper.hospital.service.UserInfoService;
import com.jumper.hospital.utils.ArrayUtils;
import com.jumper.hospital.utils.Consts;
import com.jumper.hospital.utils.TimeUtils;
import com.jumper.hospital.vo.VoUserInfo;

@Service
public class UserInfoServiceImpl extends BaseServiceImpl<UserInfo, Integer> implements UserInfoService {

	@Autowired
	private UserInfoDao userInfoDaoImpl;

	@Override
	public BaseDao<UserInfo, Integer> getBaseDAO() {
		return userInfoDaoImpl;
	}

	@Override
	public Page<VoUserInfo> findBindUserListByHospital(Page<UserInfo> page, Integer hospitalId, String searchKey,
			Integer type) {
		try {
			Page<UserInfo> data = userInfoDaoImpl.findUserListByHospital(page, hospitalId, searchKey, type);
			Page<VoUserInfo> pageData = convertData(data);
			return pageData;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Page<VoUserInfo> convertData(Page<UserInfo> page) {
		Page<VoUserInfo> pageData = new Page<VoUserInfo>();
		List<VoUserInfo> dataList = new ArrayList<VoUserInfo>();
		try {
			if (page != null && ArrayUtils.isNotEmpty(page.getResult())) {
				for (UserInfo user : page.getResult()) {
					if (user != null) {
						VoUserInfo vo = new VoUserInfo();
						vo.setId(user.getId());
						vo.setAge(user.getUserExitInfo().getAge());
						vo.setMobile(user.getMobile());
						if (user.getUserExitInfo().getCurrentIdentity() == 0) {
							vo.setPreganyDate(user.getExpectedDateOfConfinement() == null ? "--"
									: TimeUtils.convertTime(user.getExpectedDateOfConfinement(),
											Consts.FORMAT_TIME_THREE));
							vo.setPreganyWeek(user.getExpectedDateOfConfinement() == null ? "--"
									: TimeUtils.getPregnantWeek(user.getExpectedDateOfConfinement())[0] + "");
						} else {
							int[] babyInfo = TimeUtils.getBabyAgeAndMonth(user.getUserExitInfo().getBabyBirthday());
							vo.setPreganyDate(user.getUserExitInfo().getBabyBirthday() == null ? "--"
									: TimeUtils.convertTime(user.getUserExitInfo().getBabyBirthday(),
											Consts.FORMAT_TIME_THREE));
							vo.setPreganyWeek(babyInfo[0] + "岁" + babyInfo[1] + "月");
						}
						vo.setUserName(user.getUserExitInfo().getRealName());
						dataList.add(vo);
					}
				}
				pageData.setPageNo(page.getPageNo());
				pageData.setPageSize(page.getPageSize());
				pageData.setResult(dataList);
				pageData.setTotalCount(page.getTotalCount());
			}
			return pageData;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Integer> findWeekList(Integer id) {
		return userInfoDaoImpl.findWeekList(id);
	}

	@Override
	public List<Integer> findUserIdsByPregnantWeek(int pregnantWeek, int hospitalId, String flags) {
		return userInfoDaoImpl.findUserIdsByPregnantWeek(pregnantWeek, hospitalId, flags);
	}

	@Override
	public List<Integer> findBabyList(Integer id) {
		return userInfoDaoImpl.findBabyList(id);
	}

	@Override
	public List<Integer> getPregnantWeeks(Integer id) {
		List<Integer> weeks = new ArrayList<Integer>();
		List<Date> expectedDateOfPregnant = userInfoDaoImpl.getExpectedDateOfPregnant(id);
		try {
			if (ArrayUtils.isNotEmpty(expectedDateOfPregnant)) {
				for (Date date : expectedDateOfPregnant) {
					if (date != null) {
						int a[] = TimeUtils.getPregnantWeek(date);
						if (a[0] == 39 && a[1] == 6 && a[2] == 280) {

						} else {
							weeks.add(a[0]);
						}
					}
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		HashSet<Integer> set = new HashSet<Integer>(weeks);
		weeks = new ArrayList<Integer>(set);
		Collections.sort(weeks);
		return weeks;
	}

	@Override
	public List<String> getBabyAges(Integer id) {
		List<Date> babyBirths = userInfoDaoImpl.getBabyBirthdays(id);
		List<String> lists = new ArrayList<String>();
		if (ArrayUtils.isNotEmpty(babyBirths)) {
			for (Date date : babyBirths) {
				if (date != null) {
					int a[] = TimeUtils.getBabyAgeAndMonth(date);
					lists.add(a[0] + ";" + a[1]);
				}
			}
			HashSet<String> set = new HashSet<String>(lists);
			lists = new ArrayList<String>(set);
			Collections.sort(lists, new MyComparator());
		}
		return lists;
	}

	@Override
	public Map<Integer, Date> findUserIdsAndDate(Integer id, int flag) {
		Map<Integer, Date> map = new HashMap<Integer, Date>();
		List<Object> lists = userInfoDaoImpl.findUserIdsAndDate(id, flag);
		if (lists != null && lists.size() > 0) {
			if (flag == 0) {
				try {
					for (Object objects : lists) {
						map.put((Integer) objects, null);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				for (Object objects : lists) {
					Object[] object = (Object[]) objects;
					map.put((Integer) object[0], (Date) object[1]);
				}
			}
		}
		return map;
	}

	@Override
	public UserInfo findByContactPhone(String phone) {
		return userInfoDaoImpl.findByContactPhone(phone);
	}

	@Override
	public UserInfo findUserInfoById(Integer id) {
		return userInfoDaoImpl.findByUserId(id);
	}

	@Override
	public UserExtraInfo findUserExtra(Integer id) {
		// TODO Auto-generated method stub
		return userInfoDaoImpl.findUserExtra(id);
	}

	@Override
	public UserExtraInfo findUserExtra(String phone) {
		// TODO Auto-generated method stub
		return userInfoDaoImpl.findUserExtra(phone);
	}

	@Override
	public int newUserCount(String startTime, String endTime, Integer id) {
		// TODO Auto-generated method stub
		 int i = userInfoDaoImpl.newUserCount(startTime, endTime, id);
		 int j =  userInfoDaoImpl.newUserCountbyIos(startTime, endTime, id);
		return i+j;
	}

	public int allUserCount(Integer id) {
		int i = userInfoDaoImpl.allUserCount(id);
		int j = userInfoDaoImpl.allUserCountbyIos(id);
		return i+j;
	}

	public int todayNewUserCount(Integer id) {
		int i = userInfoDaoImpl.todayNewUserCount(id);
		int j = userInfoDaoImpl.todayNewUserCountbyIos(id);
		return i+j;
	}

	@Override
	public List<Hospital> findAllHospitalInfo(String hospitalName) {
		return userInfoDaoImpl.findAllHospitalInfo(hospitalName);
	}

}