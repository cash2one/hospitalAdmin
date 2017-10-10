package com.jumper.hospital.service.impl;
/**
 * 线上课程签到Service实现类 
 * @author rent
 * @date 2016-01-25
 */
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jumper.hospital.base.Page;
import com.jumper.hospital.dao.BaseDao;
import com.jumper.hospital.dao.SchoolOnlineSignDao;
import com.jumper.hospital.dao.UserInfoDao;
import com.jumper.hospital.entity.SchoolOnlineCourse;
import com.jumper.hospital.entity.SchoolOnlineSign;
import com.jumper.hospital.entity.UserInfo;
import com.jumper.hospital.service.SchoolOnlineSignService;
import com.jumper.hospital.utils.ArrayUtils;
import com.jumper.hospital.utils.BeanUtils;
import com.jumper.hospital.utils.TimeUtils;
import com.jumper.hospital.vo.school.VoOnlineSignData;

@Service
public class SchoolOnlineSignServiceImpl extends BaseServiceImpl<SchoolOnlineSign, Integer> implements SchoolOnlineSignService {

	@Autowired
	private SchoolOnlineSignDao schoolOnlineSignDaoImpl;
	@Autowired
	private UserInfoDao userInfoDaoImpl;
	
	@Override
	public BaseDao<SchoolOnlineSign, Integer> getBaseDAO() {
		return schoolOnlineSignDaoImpl;
	}

	@Override
	public Integer getSignCountByCourseId(Integer courseId) {
		return schoolOnlineSignDaoImpl.getSignCountByCourseId(courseId);
	}

	@Override
	public Page<SchoolOnlineSign> findCourseSignList(Page<SchoolOnlineSign> page, Integer courseId, String key) {
		Page<SchoolOnlineSign> pageData = schoolOnlineSignDaoImpl.findCourseSignList(page, courseId, key);
		List<SchoolOnlineSign> list = new ArrayList<SchoolOnlineSign>();
		if(pageData != null && ArrayUtils.isNotEmpty(pageData.getResult())){
			for(SchoolOnlineSign sign : pageData.getResult()){
				Integer userId = sign.getSignUserId();
				if(userId != null){
					UserInfo user = userInfoDaoImpl.get(userId);
					if (user.getExpectedDateOfConfinement() == null){
						list.add(sign);
						continue;
					}
					try {
						int[] pregnant = TimeUtils.getPregnantWeek(user.getExpectedDateOfConfinement());
						if(pregnant.length > 2){
							sign.setUserWeek(pregnant[0]+"周"+pregnant[1]+"天");
						}
						list.add(sign);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			pageData.setResult(list);
		}
		return pageData;
	}

	@Override
	public List<VoOnlineSignData> getCourseSignList(Integer courseId, SchoolOnlineCourse course) {
		List<VoOnlineSignData> dataList = new ArrayList<VoOnlineSignData>();
		if(courseId == null){
			return dataList;
		}
		List<SchoolOnlineSign> list = schoolOnlineSignDaoImpl.getCourseSignList(courseId);
		if(ArrayUtils.isNotEmpty(list)){
			for(SchoolOnlineSign sign : list){
				VoOnlineSignData vo = new VoOnlineSignData();
				if(sign != null){
					BeanUtils.copy(sign, vo);
					vo.setCourseName(course.getCourseName());
					vo.setCourseDoctor(course.getCourseDoctor());
					vo.setUserSignTime(TimeUtils.convertTime(sign.getSignTime()));
					
					Integer userId = sign.getSignUserId();
					if(userId != null){
						UserInfo user = userInfoDaoImpl.get(userId);
						if (user.getExpectedDateOfConfinement() == null){
							continue;
						}
						try {
							int[] pregnant = TimeUtils.getPregnantWeek(user.getExpectedDateOfConfinement());
							if(pregnant.length > 2){
								vo.setUserWeek(pregnant[0]+"周"+pregnant[1]+"天");
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					dataList.add(vo);
				}
			}
		}
		return dataList;
	}

}
