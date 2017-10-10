package com.jumper.hospital.dao;

import com.jumper.hospital.entity.PregnantCourseIndex;

public interface PregnantCourseIndexDao extends BaseDao<PregnantCourseIndex, Integer> {

	public PregnantCourseIndex getIndexByWeekAndType(Integer week, Integer type);
}
