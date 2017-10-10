package com.jumper.hospital.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.jumper.hospital.dao.PregnantCourseIndexDao;
import com.jumper.hospital.entity.PregnantCourseIndex;

@Repository
public class PregnantCourseIndexDaoImpl extends BaseDaoImpl<PregnantCourseIndex, Integer> implements PregnantCourseIndexDao {

	@Override
	public PregnantCourseIndex getIndexByWeekAndType(Integer week, Integer type) {
		Criteria c = createCriteria();
		c.add(Restrictions.eq("pregnantWeek", week));
		c.add(Restrictions.eq("pregnantType", type));
		c.setMaxResults(1);
		PregnantCourseIndex index = (PregnantCourseIndex) c.uniqueResult();
		return index;
	}


}
