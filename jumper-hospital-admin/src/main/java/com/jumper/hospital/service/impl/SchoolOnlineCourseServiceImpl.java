package com.jumper.hospital.service.impl;
/**
 * 线上课程库Service实现类
 * @author rent
 * @date 2016-01-25
 */
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jumper.hospital.base.Page;
import com.jumper.hospital.dao.BaseDao;
import com.jumper.hospital.dao.SchoolOnlineCourseDao;
import com.jumper.hospital.entity.SchoolOnlineCourse;
import com.jumper.hospital.enums.SchoolOnlineState;
import com.jumper.hospital.service.SchoolOnlineCourseService;
import com.jumper.hospital.service.SchoolOnlineSignService;
import com.jumper.hospital.utils.ArrayUtils;
import com.jumper.hospital.utils.BeanUtils;
import com.jumper.hospital.utils.Consts;
import com.jumper.hospital.utils.RegexUtils;
import com.jumper.hospital.utils.TimeUtils;
import com.jumper.hospital.vo.school.VoOnlineCourseData;

@Service
public class SchoolOnlineCourseServiceImpl extends BaseServiceImpl<SchoolOnlineCourse, Integer> implements SchoolOnlineCourseService {

	@Autowired
	private SchoolOnlineCourseDao schoolOnlineCourseDaoImpl;
	@Autowired
	private SchoolOnlineSignService schoolOnlineSignServiceImpl;
	
	@Override
	public BaseDao<SchoolOnlineCourse, Integer> getBaseDAO() {
		return schoolOnlineCourseDaoImpl;
	}

	@Override
	public Page<SchoolOnlineCourse> getCoursesByHospital(Integer hospitalId, Page<SchoolOnlineCourse> page, String searchKey, SchoolOnlineState courseState) {
		return schoolOnlineCourseDaoImpl.getCoursesByHospital(hospitalId, page, searchKey, courseState);
	}

	@Override
	public boolean editOnLineCourse(SchoolOnlineCourse course, Integer hospitalId) {
		try {
			if(course == null || hospitalId == null){
				return false;
			}
			if(course.getId() == null){
				course.setAddTime(TimeUtils.getCurrentTime());
				course.setCourseNo(TimeUtils.getOnLineNo());
				course.setHospitalId(hospitalId);
				String regexResult = RegexUtils.replaceVideoWidthAndHeight(course.getCourseUrl());
				course.setCourseUrl(regexResult);
				course.setCourseState(SchoolOnlineState.已上架);
				course.setShareCount(0);
				schoolOnlineCourseDaoImpl.persist(course);
				return true;
			}else{
				SchoolOnlineCourse target = schoolOnlineCourseDaoImpl.get(course.getId());
				BeanUtils.copy(course, target);
				String regexResult = RegexUtils.replaceVideoWidthAndHeight(target.getCourseUrl());
				target.setCourseUrl(regexResult);
				schoolOnlineCourseDaoImpl.persist(target);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Page<VoOnlineCourseData> getOnlineCourseSchedule(Integer hospitalId, Page<SchoolOnlineCourse> page, String searchKey, SchoolOnlineState courseState) {
		Page<SchoolOnlineCourse> sourceData = schoolOnlineCourseDaoImpl.getCoursesByHospital(hospitalId, page, searchKey, courseState);
		if(sourceData != null && sourceData.getResult() != null && ArrayUtils.isNotEmpty(sourceData.getResult())){
			Page<VoOnlineCourseData> voPage = new Page<VoOnlineCourseData>();
			voPage.setPageNo(page.getPageNo());
			voPage.setPageSize(page.getPageSize());
			voPage.setTotalCount(page.getTotalCount());
			List<VoOnlineCourseData> voList = new ArrayList<VoOnlineCourseData>();
			for(SchoolOnlineCourse course : sourceData.getResult()){
				if(course != null){
					VoOnlineCourseData vo = new VoOnlineCourseData();
					BeanUtils.copy(course, vo);
					Integer signCount = schoolOnlineSignServiceImpl.getSignCountByCourseId(course.getId());
					vo.setSignCount(signCount);
					voList.add(vo);
				}
			}
			voPage.setResult(voList);
			return voPage;
		}
		return null;
	}

	@Override
	public String operateCourse(Integer courseId, Integer state) {
		try {
			if(courseId == null || state == null){
				return Consts.PARAMS_ERROR;
			}
			SchoolOnlineCourse course = schoolOnlineCourseDaoImpl.get(courseId);
			if(state == 0){
				course.setCourseState(SchoolOnlineState.已上架);
			}else if(state == 1){
				course.setCourseState(SchoolOnlineState.已下架);
			}
			schoolOnlineCourseDaoImpl.save(course);
			return Consts.SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return Consts.ERROR;
		}
	}

	@Override
	public SchoolOnlineCourse getCourseByCourseName(String courseName,
			Integer hospitalId, Integer courseId) {
		return schoolOnlineCourseDaoImpl.getCourseByCourseName(courseName,hospitalId,courseId);
	}

}
