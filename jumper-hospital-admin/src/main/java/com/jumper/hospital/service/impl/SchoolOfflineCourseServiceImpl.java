package com.jumper.hospital.service.impl;
/**
 * 线下课程库Service实现类
 * @author rent
 * @date 2016-01-25
 */
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jumper.hospital.base.Page;
import com.jumper.hospital.dao.BaseDao;
import com.jumper.hospital.dao.SchoolCourseAppointDao;
import com.jumper.hospital.dao.SchoolOfflineCourseDao;
import com.jumper.hospital.dao.impl.SchoolCourseDetailDaoImpl;
import com.jumper.hospital.entity.SchoolOfflineCourse;
import com.jumper.hospital.service.SchoolOfflineCourseService;
import com.jumper.hospital.utils.BeanUtils;
import com.jumper.hospital.utils.TimeUtils;

@Service
public class SchoolOfflineCourseServiceImpl extends BaseServiceImpl<SchoolOfflineCourse, Integer> implements SchoolOfflineCourseService {

	@Autowired
	private SchoolOfflineCourseDao schoolOfflineCourseDaoImpl;
	@Autowired
	private SchoolCourseDetailDaoImpl schoolCourseDetailDaoImpl;
	@Autowired
	private SchoolCourseAppointDao schoolCourseAppointDaoImpl;
	
	@Override
	public BaseDao<SchoolOfflineCourse, Integer> getBaseDAO() {
		return schoolOfflineCourseDaoImpl;
	}

	@Override
	public Page<SchoolOfflineCourse> getCoursesByHospital(Integer hospitalId, Page<SchoolOfflineCourse> page, String searchKey) {
		return schoolOfflineCourseDaoImpl.getCoursesByHospital(hospitalId, page, searchKey);
	}

	@Override
	public boolean addOffLineCourse(SchoolOfflineCourse course, Integer hospitalId, Integer costType) {
		try {
			if(course == null){
				return false;
			}
			/** 新增 **/
			if(course.getId() == null){
				course.setHospitalId(hospitalId);
				course.setAddTime(TimeUtils.getCurrentTime());
				if(costType == 0){
					course.setCourseCost(0d);
				}
				course.setCourseNo(TimeUtils.getOffLineNo());
				schoolOfflineCourseDaoImpl.persist(course);
				return true;
			}else{
				/** 修改 **/
				SchoolOfflineCourse so = schoolOfflineCourseDaoImpl.get(course.getId());
				BeanUtils.copy(course, so);
				//修改课程时，不可修改免费或付费选项
				/*
				if(costType == 0){
					so.setCourseCost(0d);
				}
				*/
				SchoolOfflineCourse result = schoolOfflineCourseDaoImpl.persist(so);
				
				//同步修改 SchoolCourseDetail中所对应的字段
				schoolCourseDetailDaoImpl.updateSchoolCourseDetail(result);
				/** 同步修改SchoolCourseAppoint中所对应的字段 **/
				schoolCourseAppointDaoImpl.updateAppointCourseInfo(result);
				
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<SchoolOfflineCourse> searchCourse(Integer hospitalId, String searchKey) {
		return schoolOfflineCourseDaoImpl.searchCourse(hospitalId, searchKey);
	}

	@Override
	public SchoolOfflineCourse getCourseList(Integer hospitalId) {
		return schoolOfflineCourseDaoImpl.getCourseList(hospitalId);
	}

	@Override
	public SchoolOfflineCourse getCourseByCourseName(String courseName,
			Integer hospitalId,Integer courseId) {
		return schoolOfflineCourseDaoImpl.getCourseByCourseName(courseName,hospitalId,courseId);
	}

}
