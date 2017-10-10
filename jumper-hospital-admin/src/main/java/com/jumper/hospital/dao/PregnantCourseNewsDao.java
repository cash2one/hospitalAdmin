package com.jumper.hospital.dao;

import java.util.List;

import com.jumper.hospital.entity.PregnantCourseNews;

public interface PregnantCourseNewsDao extends BaseDao<PregnantCourseNews, Integer> {

	public Integer deleteByNewsId(String ids);
	
	public List<PregnantCourseNews> getByNewsId(Integer newsId);
	
	public Integer deleteByPregnantId(Integer newsId, Integer pId);
}
