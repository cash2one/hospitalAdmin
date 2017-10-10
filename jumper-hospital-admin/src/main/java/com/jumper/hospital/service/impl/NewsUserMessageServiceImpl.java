package com.jumper.hospital.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jumper.hospital.dao.BaseDao;
import com.jumper.hospital.dao.NewsUserMessageDao;
import com.jumper.hospital.entity.NewsUserMessage;
import com.jumper.hospital.service.NewsUserMessageService;

@Service
public class NewsUserMessageServiceImpl extends
		BaseServiceImpl<NewsUserMessage, Integer> implements
		NewsUserMessageService {
	@Autowired
	private NewsUserMessageDao newsUserMessageDao;

	@Override
	public BaseDao<NewsUserMessage, Integer> getBaseDAO() {
		return newsUserMessageDao;
	}


}
