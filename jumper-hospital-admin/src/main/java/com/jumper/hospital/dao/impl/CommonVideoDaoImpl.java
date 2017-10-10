package com.jumper.hospital.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.jumper.hospital.base.Page;
import com.jumper.hospital.dao.CommonVideoDao;
import com.jumper.hospital.entity.CommonVideo;

@Repository
public class CommonVideoDaoImpl extends BaseDaoImpl<CommonVideo, Integer> implements CommonVideoDao {

	@Override
	public Page<CommonVideo> getCommonVideo(Page<CommonVideo> page) {
		 Criteria c = createCriteria();
         return findPageByCriteria(page, c);
	}

	@Override
	public CommonVideo getVideoByVideoName(String videoName, Integer videoId) {
		Criteria c = createCriteria();
		c.add(Restrictions.eq("title", videoName));
		if(videoId != null){
			c.add(Restrictions.ne("id", videoId));
		}
		c.setMaxResults(1);
		CommonVideo course=(CommonVideo) c.uniqueResult();
		return course;
	}

}
