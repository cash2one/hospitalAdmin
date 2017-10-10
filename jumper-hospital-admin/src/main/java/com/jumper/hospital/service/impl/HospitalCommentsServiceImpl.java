package com.jumper.hospital.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jumper.hospital.dao.BaseDao;
import com.jumper.hospital.dao.HospitalCommentsDao;
import com.jumper.hospital.entity.HospitalComments;
import com.jumper.hospital.service.HospitalCommentsService;

@Service
public class HospitalCommentsServiceImpl extends BaseServiceImpl<HospitalComments, Integer> implements HospitalCommentsService{
	@Autowired
	private HospitalCommentsDao hospitalCommentsDao;

	@Override
	public BaseDao<HospitalComments, Integer> getBaseDAO() {
		return hospitalCommentsDao;
	}

	@Override
	public HospitalComments getCommentByConsId(Integer id) {
		return hospitalCommentsDao.getCommentByConsId(id);
	}

}
